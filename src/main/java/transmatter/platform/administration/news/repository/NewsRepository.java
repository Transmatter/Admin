package transmatter.platform.administration.news.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import transmatter.platform.administration.news.entity.News;

import java.util.List;

public interface NewsRepository extends MongoRepository<News,String> {
    List<News> findByTitleContaining(String title);
    List<News> findBySource(String source);
}
