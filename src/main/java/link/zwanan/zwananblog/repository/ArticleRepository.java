package link.zwanan.zwananblog.repository;

import link.zwanan.zwananblog.entity.Article;
import link.zwanan.zwananblog.model.vo.response.ArticleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("""
            select distinct a from Article a left join a.tags tags
            where a.isDelete = ?1 and( upper(a.name) like upper(concat('%', ?2, '%')) or upper(tags.name) like upper(concat('%', ?2, '%')))
            """)
    Page<Article> findByKeyword(@NonNull Integer isDelete, @Nullable String keyword, Pageable pageable);

    /**
     * 查询所有文章的年份
     */
    @Query("SELECT DISTINCT YEAR(a.createDate) FROM Article a ORDER BY YEAR(a.createDate) DESC")
    List<Integer> findYears();

    /**
     * 根据年份查询文章
     */
    @Query("""
            SELECT a
                FROM Article a
                WHERE YEAR(a.createDate) = ?1
                AND a.isDelete = 0
                ORDER BY a.createDate DESC
            """)
    List<Article> findByYear(Integer year);

    Page<Article> findByIsDelete(@NonNull Integer isDelete, Pageable pageable);
   

    Optional<Article> findByPathAndIsDelete(String name, Integer isDelete);

    Optional<Article> findByPath(String name);

    @Modifying
    @Query("""
            update Article a set a.isDelete = 1 where a.path = ?1
            """)
    int deleteByPath(String path);

    @Query("""
            select distinct a from Article a left join a.tags tags
            where a.isDelete = ?1 and tags.name = ?2
            """)
    Page<Article> findByTagName(Integer i,@NonNull String tagName, Pageable pageable);
}
