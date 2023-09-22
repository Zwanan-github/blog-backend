package link.zwanan.zwananblog.repository;

import link.zwanan.zwananblog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByNameIgnoreCase(@NonNull String name);
    boolean existsByNameIgnoreCase(@NonNull String name);
}
