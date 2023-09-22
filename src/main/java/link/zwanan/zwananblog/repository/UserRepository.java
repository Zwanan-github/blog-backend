package link.zwanan.zwananblog.repository;

import link.zwanan.zwananblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(@NonNull String username);

    Optional<User> findByUsernameAndIsDelete(String username, Integer isDelete);

    @Modifying
    @Query("update User u set u.isDelete = 1 where u.username = ?1")
    int deleteByUsername(@NonNull String username);
}
