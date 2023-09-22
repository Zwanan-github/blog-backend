package link.zwanan.zwananblog.service.Impl;

import link.zwanan.zwananblog.common.RestException;
import link.zwanan.zwananblog.entity.Article;
import link.zwanan.zwananblog.entity.Tag;
import link.zwanan.zwananblog.model.vo.request.ArticleRequest;
import link.zwanan.zwananblog.model.vo.response.ArticleInfoResponse;
import link.zwanan.zwananblog.model.vo.response.ArticleResponse;
import link.zwanan.zwananblog.repository.ArticleRepository;
import link.zwanan.zwananblog.repository.TagRepository;
import link.zwanan.zwananblog.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    final ArticleRepository articleRepository;
    private final TagRepository tagRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository,
                              TagRepository tagRepository) {
        this.articleRepository = articleRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public ArticleResponse findByPath(String path) {
        Article article = articleRepository.findByPathAndIsDelete(path, 0)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND.value(), "没有找到该文章"));
        return new ArticleResponse().copyEntity(article);
    }

    @Transactional
    @Override
    public ArticleResponse saveArticle(ArticleRequest articleRequest) {
        articleRequest.setId(null);
        Optional<Article> byPath = articleRepository.findByPath(articleRequest.getPath());
        if (byPath.isPresent())
            throw new RestException(HttpStatus.BAD_REQUEST.value(), "已存在该文章");
        Article entity = articleRequest.toEntity();
        articleRequest.getTagNames().forEach(tag -> {
            Tag findTag = tagRepository.findByNameIgnoreCase(tag);
            // Objects.requireNonNullElseGet
            entity.getTags().add(Objects.requireNonNullElseGet(findTag, () -> tagRepository.save(new Tag(0L, tag))));
        });
        Article saveRes = articleRepository.save(entity);
        return new ArticleResponse().copyEntity(saveRes);
    }

    @Transactional
    @Override
    public ArticleResponse update(ArticleRequest articleRequest) {
        Optional<Article> byPath = articleRepository.findByPath(articleRequest.getPath());
        if (byPath.isPresent() && !Objects.equals(byPath.get().getId(), articleRequest.getId()))
            throw new RestException(HttpStatus.BAD_REQUEST.value(), "已存在该文章");
        Article entity = articleRequest.toEntity();
        entity.setCreateDate(byPath.get().getCreateDate());
        articleRequest.getTagNames().forEach(tag -> {
            Tag findTag = tagRepository.findByNameIgnoreCase(tag);
            // Objects.requireNonNullElseGet
            entity.getTags().add(Objects.requireNonNullElseGet(findTag, () -> tagRepository.save(new Tag(0L, tag))));
        });
        Article saveRes = articleRepository.save(entity);
        return new ArticleResponse().copyEntity(saveRes);
    }

    @Transactional
    @Override
    public int delete(String path) {
        if (articleRepository.findByPathAndIsDelete(path, 0).isEmpty()) {
            throw new RestException(HttpStatus.NOT_FOUND.value(), "没有找到该文章");
        }
        return articleRepository.deleteByPath(path);
    }

    @Override
    public Page<ArticleInfoResponse> findPage(Pageable pageable) {
        return articleRepository.findByIsDelete(0, pageable)
                .map(article -> new ArticleInfoResponse().copyEntity(article));
    }

    @Override
    public Page<ArticleInfoResponse> findPageByKeyword(String keyword, Pageable pageable) {
        return articleRepository.findByKeyword(0, keyword, pageable)
                .map(article -> new ArticleInfoResponse().copyEntity(article));
    }

    @Override
    public Page<ArticleInfoResponse> findTagPage(String tagName, Pageable pageable) {
        return articleRepository.findByTagName(0, tagName, pageable)
                .map(article -> new ArticleInfoResponse().copyEntity(article));
    }
}
