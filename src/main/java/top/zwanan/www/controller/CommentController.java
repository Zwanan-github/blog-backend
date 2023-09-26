package top.zwanan.www.controller;

import top.zwanan.www.common.RestBean;
import top.zwanan.www.model.vo.request.CommentRequest;
import top.zwanan.www.model.vo.response.CommentResponse;
import top.zwanan.www.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    final CommentService commentService;


    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public RestBean<List<CommentResponse>> getCommentByArticleId(@PathVariable("id") Long id) {
        var commentByArticle = commentService.getCommentByArticle(id);
        System.out.println(commentByArticle);
        return RestBean.success(commentByArticle);
    }

    @PreAuthorize("hasAnyRole('comment_add')")
    @PostMapping
    public RestBean<String> comment(@RequestBody @Validated CommentRequest commentRequest) {
        var comment = commentService.save(commentRequest);
        if (null != comment) {
            return RestBean.success("评论成功" + comment);
        } else {
            return RestBean.failure(HttpStatus.BAD_REQUEST.value(), "评论失败");
        }
    }

    @PreAuthorize("hasAnyRole('comment_delete')")
    @DeleteMapping("/{id}/{username}")
    public RestBean<String> delete(@PathVariable("id") Long id,
                                   @PathVariable("username") String username) {
        if (1 == commentService.delete(username, id)) {
            return RestBean.success("删除评论成功");
        } else {
            return RestBean.failure(HttpStatus.BAD_REQUEST.value(), "删除评论失败");
        }
    }
}
