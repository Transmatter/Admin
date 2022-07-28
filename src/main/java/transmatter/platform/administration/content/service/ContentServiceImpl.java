package transmatter.platform.administration.content.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import transmatter.platform.administration.content.dao.ContentDao;
import transmatter.platform.administration.content.entity.Content;
import transmatter.platform.administration.content.entity.Image;
import transmatter.platform.administration.content.exception.ContentNotFoundException;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    ContentDao contentDao;

    // ================ progress 1 ======================

    @Override
    public Content getContent(String id) {
        Content content = contentDao.getContent(id);
        if(content == null) throw new ContentNotFoundException(id);
        return content;
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
    // ================ progress 1 ======================

    // ================ progress 2 ======================

    @Override
    public Content updateImageContent(String id, List<Image> ImageText) {
        return null;
    }

    @Override
    public Content updateContent(String id, String title, String text) {
        return null;
    }

    @Override
    public Page<Content> getAllEmptyAltNews(PageRequest page) {
        return contentDao.getAllEmptyAltNews(page);
    }

    // ================ progress 2 ======================
}
