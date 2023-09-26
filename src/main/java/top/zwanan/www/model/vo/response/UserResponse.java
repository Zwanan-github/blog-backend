package top.zwanan.www.model.vo.response;

import top.zwanan.www.entity.Role;
import top.zwanan.www.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;

@Data
@Accessors(chain = true)
public class UserResponse extends Response<User>{

    private Long uid;

    private String username;

    private String nickname;

    private Set<Role> roles;

    private Date createDate;

    private Date updateDate;

    @Override
    public UserResponse copyEntity(User user) {
        return new UserResponse()
                .setUid(user.getUid())
                .setUsername(user.getUsername())
                .setNickname(user.getNickname())
                .setRoles(user.getRoles())
                .setCreateDate(user.getCreateDate())
                .setUpdateDate(user.getUpdateDate());
    }
}
