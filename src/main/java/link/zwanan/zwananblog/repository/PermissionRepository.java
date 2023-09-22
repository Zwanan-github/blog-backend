package link.zwanan.zwananblog.repository;

import link.zwanan.zwananblog.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}