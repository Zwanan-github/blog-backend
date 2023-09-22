package link.zwanan.zwananblog.model.vo.request;

import link.zwanan.zwananblog.entity.Article;
import link.zwanan.zwananblog.entity.Comment;
import link.zwanan.zwananblog.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@Accessors
public class CommentRequest extends Request<Comment>{

    @PositiveOrZero(message = "无效的id")
    @NotNull(message = "id不能为null")
    private Long id;
    @NotBlank(message = "评论内容不能为空")
    private String content;

    @NotNull(message = "评论的文章为空")
    private ArticleCommentRequest articleComment;

    @NotNull(message = "评论的文章为空")
    private UserCommentRequest userComment;
    private Integer isDelete;

    @Override
    public Comment toEntity() {
        return new Comment()
                .setId(this.id)
                .setContent(this.content)
                .setUser(this.userComment.toEntity())
                .setArticle(this.articleComment.toEntity())
                .setIsDelete(this.isDelete);
    }
}
@Data
class UserCommentRequest extends Request<User>{
    private Long id;

    private String username;

    @Override
    public User toEntity() {
        return new User()
                .setUid(id)
                .setUsername(username);
    }
}

@Data
@Accessors(chain = true)
class ArticleCommentRequest extends Request<Article> {

    private Long id;

    @Override
    public Article toEntity() {
        return new Article()
                .setId(this.id);
    }
}
