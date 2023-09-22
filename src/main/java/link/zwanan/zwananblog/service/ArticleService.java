package link.zwanan.zwananblog.service;

import link.zwanan.zwananblog.model.vo.request.ArticleRequest;
import link.zwanan.zwananblog.model.vo.response.ArticleInfoResponse;
import link.zwanan.zwananblog.model.vo.response.ArticleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {
    ArticleResponse saveArticle(ArticleRequest articleRequest);

    ArticleResponse findByPath(String path);

    ArticleResponse update(ArticleRequest articleService);

    int delete(String path);

    Page<ArticleInfoResponse> findPage(Pageable pageable);

    Page<ArticleInfoResponse> findPageByKeyword(String keyword, Pageable pageable);

    Page<ArticleInfoResponse> findTagPage(String tagName, Pageable pageable);
}
