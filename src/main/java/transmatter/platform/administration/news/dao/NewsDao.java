package transmatter.platform.administration.news.dao;

import transmatter.platform.administration.news.entity.News;

import java.util.List;

public interface NewsDao {
    News getContent(String id);
    List<News> getAllContents();
    void deleteContent(String id);
    News updateContent(News news);
    List<News> searchContent(String title);
    List<News> getBySource(String source);
}
