package link.zwanan.zwananblog.model.vo.response;

import link.zwanan.zwananblog.entity.Article;
import link.zwanan.zwananblog.entity.Tag;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
public class ArticleResponse extends Response<Article>{

    private Long id;

    private String name;

    private String path;

    private String cover;

    private String content;

    private Set<Tag> tags;

    private Date createDate;

    private Date updateDate;

//    private Set<Comment> comments;

    @Override
    public ArticleResponse copyEntity(Article article) {
        return new ArticleResponse()
                .setId(article.getId())
                .setName(article.getName())
                .setPath(article.getPath())
                .setCover(article.getCover())
                .setContent(article.getContent())
                .setTags(article.getTags())
                .setCreateDate(article.getCreateDate())
                .setUpdateDate(article.getUpdateDate());

    }
}
