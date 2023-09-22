package link.zwanan.zwananblog.model.vo.request;

import link.zwanan.zwananblog.entity.Role;
import link.zwanan.zwananblog.entity.User;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Set;

@Data
public class UserRequest extends Request<User> {

    @PositiveOrZero(message = "无效的id")
    @NotNull(message = "id不能为null")
    private Long uid;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$", message = "账号为邮箱地址")
    private String username;

    @Size(min = 1, max = 25, message = "你的昵称应该在1~25之间")
    private String nickname;

    @Size(min = 6, max = 50, message = "密码应该在6~50之间")
    private String password;

    private Integer isDelete;

    private Set<Role> roles;

    @Override
    public User toEntity() {
        return new User()
                .setUid(this.uid)
                .setUsername(this.username)
                .setNickname(this.nickname)
                .setPassword(this.password)
                .setIsDelete(this.isDelete)
                .setRoles(this.roles);
    }
}
