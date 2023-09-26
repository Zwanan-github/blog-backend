package top.zwanan.www.controller;

import top.zwanan.www.common.RestBean;
import top.zwanan.www.model.vo.request.ArticleRequest;
import top.zwanan.www.model.vo.response.ArticleInfoResponse;
import top.zwanan.www.model.vo.response.ArticleResponse;
import top.zwanan.www.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public RestBean<Page<ArticleInfoResponse>> getPage(String keyword, Pageable pageable) {
        if (StringUtils.hasText(keyword)) {
            return RestBean.success(articleService.findPageByKeyword(keyword, pageable));
        } else {
            return RestBean.success(articleService.findPage(pageable));
        }
    }

    @GetMapping("/tag")
    public RestBean<Page<ArticleInfoResponse>> findTagPage(String tagName, Pageable pageable) {
        return RestBean.success(articleService.findTagPage(tagName, pageable));
    }

    @GetMapping("/{path}")
    public RestBean<ArticleResponse> get(@PathVariable("path") String path) {
        ArticleResponse articleResponse = articleService.findByPath(path);
        if (null != articleResponse) {
            return RestBean.success(articleResponse);
        } else {
            return RestBean.failure(HttpStatus.NOT_FOUND.value(), null);
        }
    }


    @PostMapping
    @PreAuthorize("hasAnyRole('article_add')")
    public RestBean<String> save(@RequestBody @Validated ArticleRequest articleRequest){
        // 添加文章
        ArticleResponse article = articleService.saveArticle(articleRequest);
        if (null != article) {
            return RestBean.success(article.getPath());
        }
        return RestBean.failure(HttpStatus.BAD_REQUEST.value(), "添加信息失败");
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('article_update')")
    public RestBean<String> update(@RequestBody @Validated ArticleRequest articleRequest) {
        ArticleResponse update = articleService.update(articleRequest);
        if (null != update) {
            return RestBean.success(update.getPath());
        } else {
            return RestBean.failure(HttpStatus.BAD_REQUEST.value(), "修改失败");
        }
    }

    @DeleteMapping("/{path}")
    @PreAuthorize("hasAnyRole('article_delete')")
    public RestBean<String> delete(@PathVariable("path") String path) {
        if (1 == articleService.delete(path)) {
            return RestBean.success("删除文章成功");
        } else {
            return RestBean.failure(HttpStatus.BAD_REQUEST.value(),"删除文章失败");
        }
    }

}
