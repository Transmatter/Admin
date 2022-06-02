package transmatter.sandbox.content.news.dao;

import transmatter.sandbox.content.news.entity.News;

import java.util.List;

public interface NewsDao {
    News getContent(String id);
    List<News> getAllContents();
    void deleteContent(String id);
    News updateContent(News news);
}
