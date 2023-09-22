package link.zwanan.zwananblog.service.Impl;

import link.zwanan.zwananblog.model.vo.response.ArchiveResponse;
import link.zwanan.zwananblog.model.vo.response.ArticleResponse;
import link.zwanan.zwananblog.repository.ArticleRepository;
import link.zwanan.zwananblog.service.ArchiveService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArchiveServiceImpl implements ArchiveService {

    final ArticleRepository articleRepository;

    public ArchiveServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<ArchiveResponse> getArchive() {
        return articleRepository.findYears()
                .stream()
                .map(year -> new ArchiveResponse(year,
                                articleRepository
                                        .findByYear(year)
                                        .stream()
                                        .map(article -> {
                                            article.setContent(null);
                                            return new ArticleResponse().copyEntity(article);
                                        })
                                        .collect(Collectors.toList())
                        )
                )
                .collect(Collectors.toList());
    }
}
