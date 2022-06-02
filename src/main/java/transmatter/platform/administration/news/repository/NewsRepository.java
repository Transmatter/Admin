package transmatter.sandbox.content.news.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import transmatter.sandbox.content.news.entity.News;

public interface NewsRepository extends MongoRepository<News,String> {
}
