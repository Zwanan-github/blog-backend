package top.zwanan.www.repository;

import top.zwanan.www.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(@NonNull String username);

    Optional<User> findByUsernameAndIsDelete(String username, Integer isDelete);

    @Modifying
    @Query("update User u set u.isDelete = 1 where u.username = ?1")
    int deleteByUsername(@NonNull String username);
}
