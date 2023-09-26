package top.zwanan.www.service;

import top.zwanan.www.model.vo.request.UserRequest;
import top.zwanan.www.model.vo.response.UserResponse;

public interface UserService {

    UserResponse findByUsername(String username);

    UserResponse updateUserInfo(UserRequest user);

    UserResponse saveUser(UserRequest user);

    int deleteByUsername(String username);
}
