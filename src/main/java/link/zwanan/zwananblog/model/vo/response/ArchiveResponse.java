package link.zwanan.zwananblog.model.vo.response;

import link.zwanan.zwananblog.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class ArchiveResponse {

    private Integer year;

    private List<ArticleResponse> articleList;
}
