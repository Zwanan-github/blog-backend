package top.zwanan.www.model.vo.response;

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
