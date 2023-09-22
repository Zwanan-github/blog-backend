package link.zwanan.zwananblog.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "user", indexes = {
        @Index(name = "idx_user_user_name_unq", columnList = "user_name", unique = true)
})
public class User {
    @Id
    // 自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long uid;

    //用户登录凭证
    @Column(name = "user_name", unique = true)
    private String username;

    @Column(name = "nick_name", nullable = false)
    private String nickname;

    @Column(name = "pass_word", nullable = false)
    private String password;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createDate;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateDate;

    @Column(name = "is_delete", columnDefinition = "int default 0")
    private Integer isDelete;

    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    )
    //多方默认懒加载, 在Service层加上@Transactional保持session
    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return getUid() != null && Objects.equals(getUid(), user.getUid());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
