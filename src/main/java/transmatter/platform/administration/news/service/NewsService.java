package transmatter.platform.administration.news.service;

import transmatter.platform.administration.news.entity.Image;
import transmatter.platform.administration.news.entity.News;

import java.util.List;

public interface NewsService {
    News getContent(String id);
    List<News> getAllContents();
    News deleteContent(String id);
    List<News> getAllEmptyAltNews();
    News updateImageContent(String id,List<Image> ImageText);
}

