package transmatter.platform.administration.news.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import transmatter.platform.administration.news.entity.News;

public interface NewsRepository extends MongoRepository<News,String> {
}
