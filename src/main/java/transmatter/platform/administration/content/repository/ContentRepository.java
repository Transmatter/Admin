package transmatter.platform.administration.content.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import transmatter.platform.administration.content.entity.Content;
import transmatter.platform.administration.content.entity.ContentStatus;
import transmatter.platform.administration.content.entity.ContentType;

public interface ContentRepository extends MongoRepository<Content,String> {
    // progress 1
    // admin feature
    Page<Content> findByTitleContaining(String title, Pageable page);
    Page<Content> findBySourceAndCategory(String source, String category, Pageable page);
    Page<Content> findBySource(String source, Pageable page);

    // =================================================================================

    // progress 2
    // admin feature
    Page<Content> findByPublicDateBetween(String start, String end, Pageable page);
    Page<Content> findByApproveStatus(ContentStatus status, Pageable page);
    Page<Content> findByType(ContentType type, Pageable page);
    Page<Content> findByTitleContainingAndSourceAndCategory(String title, String source, String category, Pageable page);
    Page<Content> findByTitleContainingAndSource(String title, String source, Pageable page);


    // progress 2
    // vi feature
    Page<Content> findByTitleContainingAndApproveStatus(String title, ContentStatus approveStatus, Pageable page);
    Page<Content> findBySourceAndApproveStatus(String source, ContentStatus approveStatus, Pageable page);
    Page<Content> findBySourceAndCategoryAndApproveStatus(String source, String type, ContentStatus approveStatus, Pageable page);
    Page<Content> findByApprovedDateBetween(String start, String end, Pageable page);
    Page<Content> findByTitleContainingAndSourceAndCategoryAndApproveStatus(String title, String source, String category,ContentStatus approveStatus, Pageable page);
    Page<Content> findByTitleContainingAndSourceAndApproveStatus(String title, String source, ContentStatus approveStatus, Pageable page);
}
