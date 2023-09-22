package link.zwanan.zwananblog.model.vo.response;

import link.zwanan.zwananblog.entity.Comment;
import link.zwanan.zwananblog.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class CommentResponse extends Response<Comment> {

    private Long id;

    private String content;

    private UserCommentResponse user;

    private Date createDate;

    @Override
    public CommentResponse copyEntity(Comment comment) {
        return new CommentResponse()
                .setId(comment.getId())
                .setContent(comment.getContent())
                .setUser(new UserCommentResponse().copyEntity(comment.getUser()))
                .setCreateDate(comment.getCreateTime());
    }
}
@Accessors(chain = true)
@Data
class UserCommentResponse extends Response<User>{
    Long id;
    String nickname;
    String username;

    @Override
    public UserCommentResponse copyEntity(User user) {
        return new UserCommentResponse()
                .setId(user.getUid())
                .setUsername(user.getUsername())
                .setNickname(user.getNickname());
    }
}
