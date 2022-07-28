package transmatter.platform.administration.content.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import transmatter.platform.administration.content.dao.ContentDao;
import transmatter.platform.administration.content.entity.Content;
import transmatter.platform.administration.content.entity.Image;
import transmatter.platform.administration.content.exception.ContentNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    ContentDao contentDao;

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

//    @Override
//    public List<News> getAllContents(){
//        return newsDao.getAllContents();
//    }
//
    @Override
    public Content deleteContent(String id) {
        Content content = contentDao.getContent(id);
        contentDao.deleteContent(id);
        return content;
    }

    @Override
    public Page<Content> getAllEmptyAltNews(PageRequest page) {
        return contentDao.getAllEmptyAltNews(page);
    }

//    @Override
//    public News updateImageContent(String id, List<Image> ImageText) {
//        News news = newsDao.getContent(id);
//        for(int i=0;i<ImageText.size();i++){
//            news.getImages().get(i).setAlt(ImageText.get(i).getAlt());
//        }
//        return newsDao.updateContent(news);
//    }
//
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
}
