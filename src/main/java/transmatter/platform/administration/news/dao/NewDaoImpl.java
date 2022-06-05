package transmatter.platform.administration.news.dao;


import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import transmatter.platform.administration.news.entity.News;
import transmatter.platform.administration.news.repository.NewsRepository;

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

    @Override
    public List<News> searchContent(String title) {
        return newsRepository.findByTitleContaining(title);
    }

    @Override
    public List<News> getBySource(String source) {
        return newsRepository.findBySource(source);
    }
}