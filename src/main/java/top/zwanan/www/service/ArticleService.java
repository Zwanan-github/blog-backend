package top.zwanan.www.service;

import top.zwanan.www.model.vo.request.ArticleRequest;
import top.zwanan.www.model.vo.response.ArticleInfoResponse;
import top.zwanan.www.model.vo.response.ArticleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {
    ArticleResponse saveArticle(ArticleRequest articleRequest);

    ArticleResponse findByPath(String path);

    ArticleResponse update(ArticleRequest articleService);

    int delete(String path);

    Page<ArticleInfoResponse> findPage(Pageable pageable);

    Page<ArticleInfoResponse> findPageByKeyword(String keyword, Pageable pageable);

    Page<ArticleInfoResponse> findTagPage(String tagName, Pageable pageable);
}
