package transmatter.platform.administration.content.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import transmatter.platform.administration.content.entity.Content;

import java.util.List;

public interface ContentDao {
    // progress 1
    Content getContent(String id); // both admin and vi
    Page<Content> getAllContents(PageRequest page); // both admin and vi
    List<Content> getAllContents(); // both admin and vi
    void deleteContent(String id); // only admin
    Page<Content> searchContent(String title, PageRequest page); // both admin and vi
    Page<Content> getBySource(String source, PageRequest page); // both admin and vi
    Page<Content> getBySourceAndType(String source, String type, PageRequest page); // both admin and vi

    // progress 2
    Content updateContent(Content news); // only admin
    Page<Content> getAllEmptyAltNews(PageRequest page); // only admin
    Page<Content> getContentByDate(String start, String end, PageRequest page); // only admin

    Page<Content> getAllApproveContent(PageRequest page); // only vi
    Page<Content> getApproveContentBySource(String source, PageRequest page); // only vi
    Page<Content> getApproveContentByDate(String start, String end, PageRequest page); // only vi
    Page<Content> searchOnlyApproveContent(String title, PageRequest page); // only vi
    Page<Content> getOnlyApproveContentBySource(String source,String type, PageRequest page); // only vi
}
