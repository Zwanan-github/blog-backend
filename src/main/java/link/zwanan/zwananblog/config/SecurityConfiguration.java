package link.zwanan.zwananblog.config;

import com.alibaba.fastjson.JSONObject;
import link.zwanan.zwananblog.common.RestBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 提供认证管理器的 Bean
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.anonymous().disable();
        http.authorizeHttpRequests(request -> request
                .antMatchers(HttpMethod.GET).permitAll()
                .antMatchers("/static/**", "/auth/**").permitAll()
                .anyRequest().authenticated()
        );
        // 登录url设置
        http.formLogin(form -> form
                .loginProcessingUrl("/auth/login")
                .successHandler((request, response, authentication) -> {
                    User principal = (User) authentication.getPrincipal();
                    WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
                    log.info("ip:[{}],用户:[{}]登录成功，权限:{}", details.getRemoteAddress(), principal.getUsername(), principal.getAuthorities());
                    writeResponse(response, RestBean.success("登录成功"));
                })
                .failureHandler((request, response, authentication) -> {
                    writeResponse(response, RestBean.failure(HttpStatus.UNAUTHORIZED.value(), authentication.getMessage()));
                })
        );
        // remember-me
        http.rememberMe(remember -> {
            remember.key("Zwanan-access-key").tokenValiditySeconds(86400);
        });
        // 登出
        http.logout(logout -> logout
                .logoutUrl("/auth/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    User principal = (User) authentication.getPrincipal();
                    WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
                    log.info("ip:[{}],用户:[{}]退出登录，权限:{}", details.getRemoteAddress(), principal.getUsername(), principal.getAuthorities());
                    writeResponse(response, RestBean.success("退出登录"));
                })
        );
        http.csrf().disable();
        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint((request, response, authentication) -> {
                    writeResponse(response, RestBean.failure(HttpStatus.UNAUTHORIZED.value(), "请登录"));
                })
                .accessDeniedHandler((request, response, authentication) -> {
                    log.error("权限不足：{}", authentication.getMessage());
                    writeResponse(response, RestBean.failure(HttpStatus.UNAUTHORIZED.value(), "权限不够"));
                })
        );
        return http.build();
    }

    private void writeResponse(HttpServletResponse response, RestBean<String> restBean) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(JSONObject.toJSONString(restBean));
    }

}
