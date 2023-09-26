package top.zwanan.www.service;

import top.zwanan.www.model.vo.request.CommentRequest;
import top.zwanan.www.model.vo.response.CommentResponse;

import java.util.List;

public interface CommentService {
    List<CommentResponse> getCommentByArticle(Long id);

    CommentResponse save(CommentRequest commentRequest);

    int delete(String username, Long id);
}
