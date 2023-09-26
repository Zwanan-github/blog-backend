package top.zwanan.www.service.Impl;

import top.zwanan.www.common.RestException;
import top.zwanan.www.entity.User;
import top.zwanan.www.model.vo.request.UserRequest;
import top.zwanan.www.model.vo.response.UserResponse;
import top.zwanan.www.repository.UserRepository;
import top.zwanan.www.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse findByUsername(String username) {
        User user = userRepository.findByUsernameAndIsDelete(username, 0)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND.value(), "没有找到该用户"));
        return new UserResponse().copyEntity(user);
    }

    @Transactional
    @Override
    public UserResponse updateUserInfo(UserRequest user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new UserResponse().copyEntity(userRepository.save(user.toEntity()));
    }

    @Transactional
    @Override
    public UserResponse saveUser(UserRequest user) {
        user.setUid(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            throw new RestException(HttpStatus.BAD_REQUEST.value(), "该邮箱已注册过");
        return new UserResponse().copyEntity(userRepository.save(user.toEntity()));
    }

    @Transactional
    @Override
    public int deleteByUsername(String username) {
        if (userRepository.findByUsernameAndIsDelete(username, 0).isEmpty()) {
            throw new RestException(HttpStatus.NOT_FOUND.value(), "没有找到该用户");
        }
        return userRepository.deleteByUsername(username);
    }


}
