package top.zwanan.www.controller;

import top.zwanan.www.common.RestBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping
    public RestBean<Boolean> check() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return RestBean.success(null != authentication && authentication.isAuthenticated());
    }

    @GetMapping("/permission")
    public RestBean<List<String>> getPermission() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null == authentication) {
            return RestBean.success(new ArrayList<>());
        }
        List<String> permission = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return RestBean.success(permission);
    }
}
