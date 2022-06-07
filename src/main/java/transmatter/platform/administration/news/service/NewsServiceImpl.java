package transmatter.platform.administration.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transmatter.platform.administration.news.dao.NewsDao;
import transmatter.platform.administration.news.entity.Image;
import transmatter.platform.administration.news.entity.News;
import transmatter.platform.administration.news.exception.NewsNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsDao newsDao;

    @Override
    public News getContent(String id) {
        News news = newsDao.getContent(id);
        if(news == null) throw new NewsNotFoundException(id);
        return news;
    }

    @Override
    public List<News> getAllContents() {
        return newsDao.getAllContents();
    }

    @Override
    public News deleteContent(String id) {
        News news = newsDao.getContent(id);
        newsDao.deleteContent(id);
        return news;
    }

    @Override
    public List<News> getAllEmptyAltNews() {
        List<News> emptyAlt = new ArrayList<>();
        for (News news: newsDao.getAllContents()) {
            for(Image img : news.getImages()) {
                if(img.getAlt().length() == 0){
                    emptyAlt.add(news);
                    break;
                }
            }
        }
        return emptyAlt;
    }

    @Override
    public News updateImageContent(String id, List<Image> ImageText) {
        News news = newsDao.getContent(id);
        for(int i=0;i<ImageText.size();i++){
            news.getImages().get(i).setAlt(ImageText.get(i).getAlt());
        }
        return newsDao.updateContent(news);
    }

    @Override
    public List<News> searchNews(String title) {
        return newsDao.searchContent(title);
    }

    @Override
    public List<News> getNewsBySource(String source) {
        return newsDao.getBySource(source);
    }
}
