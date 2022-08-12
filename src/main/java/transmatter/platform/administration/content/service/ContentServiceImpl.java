package transmatter.platform.administration.content.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import transmatter.platform.administration.content.dao.ContentDao;
import transmatter.platform.administration.content.entity.Content;
import transmatter.platform.administration.content.entity.Image;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    ContentDao contentDao;

    // ================ progress 1 ================================

    @Override
    public Content getContent(String id) {
        return contentDao.getContent(id);
    }

    @Override
    public Page<Content> getAllContents(PageRequest page) {
        return contentDao.getAllContents(page);
    }

    @Override
    public Content deleteContent(String id) {
        Content content = contentDao.getContent(id);
        contentDao.deleteContent(id);
        return content;
    }

    @Override
    public Page<Content> searchNews(String title, PageRequest page) {
        return contentDao.searchContent(title,page);
    }

    @Override
    public Page<Content> getNewsBySource(String source, PageRequest page) {
        return contentDao.getBySource(source,page);
    }

    @Override
    public Page<Content> getNewsBySourceAndType(String source, String type, PageRequest page) {
        return contentDao.getBySourceAndType(source,type,page);
    }
    // ================ progress 1 =================================

    // ================ progress 2 admin part ======================

    @Override
    public Content updateImageContent(String id, List<Image> ImageText) {
        Content content = contentDao.getContent(id);
        for(int i=0;i<content.getImages().size();i++){
            content.getImages().get(i).setAlt(ImageText.get(i).getAlt());
        }
        return contentDao.updateContent(content);
    }

    @Override
    public Content updateContent(String id, String title, String text) {
        Content content = contentDao.getContent(id);
        content.setTitle(title);
        content.setContent(text);
        return contentDao.updateContent(content);
    }

    @Override
    public Page<Content> getAllEmptyAltNews(PageRequest page) {
        return contentDao.getAllEmptyAltNews(page);
    }

    @Override
    public Page<Content> getContentByDate(String start, String end, PageRequest page) {
        return null;
    }

    // ================== progress 2 vi part ======================

    @Override
    public Page<Content> getAllApproveContent(PageRequest page) {
        return null;
    }

    @Override
    public Page<Content> getApproveContentByDate(String start, String end, PageRequest page) {
        return null;
    }

    @Override
    public Page<Content> searchOnlyApproveContent(String title, PageRequest page) {
        return null;
    }

    @Override
    public Page<Content> getOnlyApproveContentBySource(String source, String type, PageRequest page) {
        return null;
    }

    // ================ progress 2 ======================
}
