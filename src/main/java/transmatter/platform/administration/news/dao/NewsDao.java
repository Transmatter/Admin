package transmatter.platform.administration.news.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import transmatter.platform.administration.news.entity.News;

import java.util.List;

public interface NewsDao {
    News getContent(String id);
    Page<News> getAllContents(PageRequest page);
    List<News> getAllContents();
    void deleteContent(String id);
//    News updateContent(News news);
    Page<News> searchContent(String title,PageRequest page);
    Page<News> getBySource(String source,PageRequest page);
    Page<News> getBySourceAndType(String source, String type, PageRequest page);
}
