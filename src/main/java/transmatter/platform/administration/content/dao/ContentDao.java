package transmatter.platform.administration.content.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import transmatter.platform.administration.content.entity.Content;

import java.util.List;

public interface ContentDao {
    // progress 1
    Content getContent(String id);
    Page<Content> getAllContents(PageRequest page);
    List<Content> getAllContents();
    void deleteContent(String id);
    Page<Content> searchContent(String title, PageRequest page);
    Page<Content> getBySource(String source, PageRequest page);
    Page<Content> getBySourceAndType(String source, String type, PageRequest page);

    // progress 2
    Content updateContent(Content news);
    Page<Content> getAllEmptyAltNews(PageRequest page);
}
