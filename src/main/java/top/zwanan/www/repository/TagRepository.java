package top.zwanan.www.repository;

import top.zwanan.www.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByNameIgnoreCase(@NonNull String name);
    boolean existsByNameIgnoreCase(@NonNull String name);
}
