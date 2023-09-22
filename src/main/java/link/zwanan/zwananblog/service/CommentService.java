package link.zwanan.zwananblog.service;

import link.zwanan.zwananblog.model.vo.request.CommentRequest;
import link.zwanan.zwananblog.model.vo.response.CommentResponse;

import java.util.List;

public interface CommentService {
    List<CommentResponse> getCommentByArticle(Long id);

    CommentResponse save(CommentRequest commentRequest);

    int delete(String username, Long id);
}
