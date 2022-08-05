package transmatter.platform.administration.content.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import transmatter.platform.administration.content.entity.Content;
import transmatter.platform.administration.content.entity.Image;

import java.util.List;

public interface ContentService {
    // progress 1
    Content getContent(String id);
    Page<Content> getAllContents(PageRequest page);
    Content deleteContent(String id);
    Page<Content> searchNews(String title, PageRequest page);
    Page<Content> getNewsBySource(String source, PageRequest page);
    Page<Content> getNewsBySourceAndType(String source, String type, PageRequest page);

    // progress 2
    Content updateImageContent(String id, List<Image> ImageText);
    Content updateContent(String id, String title, String text);
    Page<Content> getAllEmptyAltNews(PageRequest page);
}

