package link.zwanan.zwananblog.service.Impl;

import link.zwanan.zwananblog.model.vo.request.CommentRequest;
import link.zwanan.zwananblog.model.vo.response.CommentResponse;
import link.zwanan.zwananblog.repository.CommentRepository;
import link.zwanan.zwananblog.service.CommentService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentResponse> getCommentByArticle(Long id) {
        return commentRepository.findByArticle_IdAndIsDeleteOrderByCreateTimeDesc(id, 0)
                .stream()
                .map(comment -> new CommentResponse().copyEntity(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CommentResponse save(CommentRequest commentRequest) {
        commentRequest.setId(null);
        return new CommentResponse().copyEntity(commentRepository.save(commentRequest.toEntity()));
    }

    @Transactional
    @Override
    public int delete(String username, Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!username.equals(user.getUsername())) {
            return 0;
        }
        return commentRepository.delete(id);
    }
}
