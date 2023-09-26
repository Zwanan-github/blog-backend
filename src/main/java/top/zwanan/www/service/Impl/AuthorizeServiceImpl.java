package top.zwanan.www.service.Impl;

import top.zwanan.www.common.RestException;
import top.zwanan.www.entity.Permission;
import top.zwanan.www.entity.Role;
import top.zwanan.www.repository.UserRepository;
import top.zwanan.www.service.AuthorizeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    final UserRepository userRepository;

    public AuthorizeServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //多方默认懒加载, 在Service层加上@Transactional保持session
    // @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("用户名不能为空!");
        }
        // 获取当前用户的
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("账号或密码错误"));
        if (1 == user.getIsDelete()) {
            throw new RestException(HttpStatus.UNAUTHORIZED.value(), "用户被警用");
        }
        String[] roles = user.getRoles().stream()
                .map(Role::getRName)
                .toArray(String[]::new);
        String[] permissions = user.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(Permission::getPName)
                .toArray(String[]::new);
        return User
                .withUsername(username)
                .password(user.getPassword())
                .roles(permissions)
                .build();
    }
}
