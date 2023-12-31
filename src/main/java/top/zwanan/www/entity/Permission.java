package top.zwanan.www.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Integer pid;

    @Column(name = "permission_name", unique = true)
    private String pName;

    @Column(name = "is_delete", columnDefinition = "int default 0")
    private Integer isDelete;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Permission that = (Permission) o;
        return getPid() != null && Objects.equals(getPid(), that.getPid());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
