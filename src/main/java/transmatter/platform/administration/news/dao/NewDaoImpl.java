package transmatter.platform.administration.news.dao;


import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<News> getAllContents(PageRequest pageRequest) {
        return newsRepository.findAll(pageRequest);
    }

    @Override
    public List<News> getAllContents(){
        return newsRepository.findAll();
    }

    @Override
    public void deleteContent(String id) {
        newsRepository.deleteById(id);
    }

//    @Override
//    public News updateContent(News news) {
//        return newsRepository.save(news);
//    }

    @Override
    public Page<News> searchContent(String title, PageRequest page) {
        return newsRepository.findByTitleContaining(title,page);
    }

    @Override
    public Page<News> getBySource(String source,PageRequest page) {
        return newsRepository.findBySource(source,page);
    }

    @Override
    public Page<News> getBySourceAndType(String source, String type, PageRequest page) {
        return newsRepository.findBySourceAndType(source,type,page);
    }
}
