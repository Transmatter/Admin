package transmatter.platform.administration.news.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import transmatter.platform.administration.news.entity.Image;
import transmatter.platform.administration.news.entity.News;

import java.util.List;

public interface NewsService {
    News getContent(String id);
    Page<News> getAllContents(PageRequest page);
    List<News> getAllContents();
    News deleteContent(String id);
    List<News> getAllEmptyAltNews();
    News updateImageContent(String id,List<Image> ImageText);
    Page<News> searchNews(String title,PageRequest page);
    Page<News> getNewsBySource(String source,PageRequest page);
}

