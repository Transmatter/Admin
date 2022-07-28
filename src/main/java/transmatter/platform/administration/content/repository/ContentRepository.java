package transmatter.platform.administration.content.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import transmatter.platform.administration.content.entity.Content;

public interface ContentRepository extends MongoRepository<Content,String> {
    Page<Content> findByTitleContaining(String title, Pageable page);
    Page<Content> findBySource(String source, Pageable page);
    Page<Content> findBySourceAndType(String source, String type, Pageable page);
}
