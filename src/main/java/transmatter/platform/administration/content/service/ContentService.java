package transmatter.platform.administration.content.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import transmatter.platform.administration.content.entity.Content;
import transmatter.platform.administration.content.entity.ContentStatus;
import transmatter.platform.administration.content.entity.ContentType;
import transmatter.platform.administration.content.entity.Image;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ContentService {
    // progress 1
    Content getContent(String id);
    Page<Content> getAllContents(PageRequest page);
    Content deleteContent(String id);
    Page<Content> searchNews(String title, PageRequest page);
    Page<Content> getNewsBySourceAndType(String source, String type, PageRequest page);

    // progress 2
    Content updateImageContent(String id, List<Image> ImageText, HttpServletRequest header);
    Content updateContent(String id, String title, String text);
    Page<Content> getAllEmptyAltNews(PageRequest page);

    Page<Content> getContentByDate(String start, String end, PageRequest page); // only admin
    Page<Content> getContentByType(ContentType type, PageRequest page); // only admin
    Page<Content> searchContentSpecInSrcAndCate(String title, String source, String category, PageRequest page); // only admin

    Page<Content> getAllApproveContent(PageRequest page); // only vi
    Page<Content> getApproveContentByDate(String start, String end, PageRequest page); // only vi
    Page<Content> searchOnlyApproveContent(String title, PageRequest page); // only vi
    Page<Content> getOnlyApproveContentBySource(String source,String type, PageRequest page); // only vi
    Page<Content> searchApproveContentSpecInSrcAndCate(String title, String source, String category, PageRequest page); // only vi
}

