package link.zwanan.zwananblog.model.vo.response;

import link.zwanan.zwananblog.entity.Article;
import link.zwanan.zwananblog.entity.Tag;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;

@Data
@Accessors(chain = true)
public class ArticleInfoResponse extends Response<Article>{
    private Long id;

    private String name;

    private String path;

    private String cover;

    private Set<Tag> tags;

    private Date createDate;

    private Date updateDate;

    @Override
    public ArticleInfoResponse copyEntity(Article article) {
        return new ArticleInfoResponse()
                .setId(article.getId())
                .setName(article.getName())
                .setPath(article.getPath())
                .setCover(article.getCover())
                .setTags(article.getTags())
                .setCreateDate(article.getCreateDate())
                .setUpdateDate(article.getUpdateDate());

    }
}
