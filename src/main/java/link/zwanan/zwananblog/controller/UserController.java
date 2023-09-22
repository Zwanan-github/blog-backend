package link.zwanan.zwananblog.controller;

import link.zwanan.zwananblog.common.RestBean;
import link.zwanan.zwananblog.entity.User;
import link.zwanan.zwananblog.model.vo.request.UserRequest;
import link.zwanan.zwananblog.model.vo.response.UserResponse;
import link.zwanan.zwananblog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{user_name}")
    public RestBean<UserResponse> getUserByUsername(@PathVariable("user_name") String username) {
        // 获取用户信息
        UserResponse user = userService.findByUsername(username);
        if (user != null) {
            return RestBean.success(user);
        } else {
            return RestBean.failure(HttpStatus.NOT_FOUND.value(),null);
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('user_update')")
    public RestBean<String> update(@RequestBody @Validated UserRequest user) {
        // 修改
        if (null != userService.updateUserInfo(user)) {
            return RestBean.success("修改信息成功");
        }
        return RestBean.failure(HttpStatus.BAD_REQUEST.value(), "修改信息失败");
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('user_add')")
    public RestBean<String> save(@RequestBody @Validated UserRequest user) {
        // 添加默认用户
        UserResponse respUser = userService.saveUser(user);
        if (null != respUser) {
            return RestBean.success("添加信息成功:" + respUser + (respUser.getRoles() == null ? "" : respUser.getRoles()));
        }
        return RestBean.failure(HttpStatus.BAD_REQUEST.value(), "添加信息失败");
    }

    @DeleteMapping("/{user_name}")
    @PreAuthorize("hasAnyRole('user_delete')")
    public RestBean<String> deleteByUsername(@PathVariable("user_name") String username) {
        if (1 == userService.deleteByUsername(username)) {
            return RestBean.success("删除用户成功");
        } else {
            return RestBean.failure(HttpStatus.BAD_REQUEST.value(),"删除信息失败");
        }
    }

}
