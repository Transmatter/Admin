package transmatter.platform.administration.news.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import transmatter.platform.administration.news.entity.News;

import java.util.List;

public interface NewsRepository extends MongoRepository<News,String> {
    /**
     * this method docker
     * @param title
     * @param page
     * @return
     */
    Page<News> findByTitleContaining(String title, Pageable page);
    Page<News> findBySource(String source,Pageable page);
    Page<News> findBySourceAndType(String source,String type,Pageable page);
}
