package link.zwanan.zwananblog.service;

import link.zwanan.zwananblog.entity.User;
import link.zwanan.zwananblog.model.vo.request.UserRequest;
import link.zwanan.zwananblog.model.vo.response.UserResponse;

public interface UserService {

    UserResponse findByUsername(String username);

    UserResponse updateUserInfo(UserRequest user);

    UserResponse saveUser(UserRequest user);

    int deleteByUsername(String username);
}
