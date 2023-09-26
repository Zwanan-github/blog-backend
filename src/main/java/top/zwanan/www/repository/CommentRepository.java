package top.zwanan.www.repository;


import top.zwanan.www.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticle_IdAndIsDeleteOrderByCreateTimeDesc(@NonNull Long id, @NonNull Integer isDelete);

    @Modifying
    @Query("""
            update Comment c set c.isDelete = 1 where c.id = ?1
            """)
    int delete(Long id);
}
