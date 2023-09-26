package top.zwanan.www.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer rid;

    @Column(name = "role_name", unique = true)
    private String rName;

    @Column(name = "is_delete", columnDefinition = "int default 0")
    private Integer isDelete;

    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "permission_id")
    )
    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Permission> permissions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return getRid() != null && Objects.equals(getRid(), role.getRid());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
