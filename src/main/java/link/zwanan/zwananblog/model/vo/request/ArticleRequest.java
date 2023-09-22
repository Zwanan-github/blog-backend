package link.zwanan.zwananblog.model.vo.request;

import link.zwanan.zwananblog.entity.Article;
import link.zwanan.zwananblog.entity.Tag;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class ArticleRequest extends Request<Article>{

    @PositiveOrZero(message = "无效的id")
    @NotNull(message = "id不能为null")
    private Long id;

    @Size(min = 1, max = 50, message = "文章标题长度限制为1~50")
    private String name;

    @Size(min = 1, max = 50, message = "文章路径长度限制为1~50")
    @Pattern(regexp = "^[a-z0-9:@._-]+$", message = "路径只允许小写字母,数字,冒号,@,英文点,下划线,分隔符")
    private String path;

    private String cover;

    @NotBlank(message = "文章内容不能为空")
    private String content;

    private Set<String> tagNames;

//    private Set<Comment> comments;

    private Integer isDelete = 0;

    @Override
    public Article toEntity() {
        return new Article()
                .setId(this.id)
                .setName(this.name)
                .setPath(this.path)
                .setCover(this.cover)
                .setContent(this.content)
                //.setComments(comments)
                .setIsDelete(this.isDelete);
    }
}
