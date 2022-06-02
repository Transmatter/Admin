package transmatter.sandbox.content.news.service;

import transmatter.sandbox.content.news.entity.Image;
import transmatter.sandbox.content.news.entity.News;

import java.util.List;

public interface NewsService {
    News getContent(String id);
    List<News> getAllContents();
    News deleteContent(String id);
    List<News> getAllEmptyAltNews();
    News updateImageContent(String id,List<Image> ImageText);
}

