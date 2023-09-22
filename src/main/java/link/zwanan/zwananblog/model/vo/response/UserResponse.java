package link.zwanan.zwananblog.model.vo.response;

import link.zwanan.zwananblog.entity.Role;
import link.zwanan.zwananblog.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
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
