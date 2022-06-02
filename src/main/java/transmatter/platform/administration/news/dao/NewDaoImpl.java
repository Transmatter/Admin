package transmatter.sandbox.content.news.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import transmatter.sandbox.content.news.entity.News;
import transmatter.sandbox.content.news.repository.NewsRepository;

import java.util.List;

@Repository
public class NewDaoImpl implements NewsDao {
    @Autowired
    NewsRepository newsRepository;

    @Override
    public News getContent(String id) {
        return newsRepository.findById(id).orElse(null);
    }

    @Override
    public List<News> getAllContents() {
        return newsRepository.findAll();
    }

    @Override
    public void deleteContent(String id) {
        newsRepository.deleteById(id);
    }

    @Override
    public News updateContent(News news) {
        return newsRepository.save(news);
    }
}
