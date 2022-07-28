package transmatter.platform.administration.content.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import transmatter.platform.administration.content.entity.Content;

public interface ContentService {
    Content getContent(String id);
    Page<Content> getAllContents(PageRequest page);
//    List<News> getAllContents();
    Content deleteContent(String id);
    Page<Content> getAllEmptyAltNews(PageRequest page);
//    News updateImageContent(String id,List<Image> ImageText);
    Page<Content> searchNews(String title, PageRequest page);
    Page<Content> getNewsBySource(String source, PageRequest page);
    Page<Content> getNewsBySourceAndType(String source, String type, PageRequest page);
}

