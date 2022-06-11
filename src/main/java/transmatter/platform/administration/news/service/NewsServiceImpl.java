package transmatter.platform.administration.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import transmatter.platform.administration.news.dao.NewsDao;
import transmatter.platform.administration.news.entity.Image;
import transmatter.platform.administration.news.entity.News;
import transmatter.platform.administration.news.exception.NewsNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    /**
     * This Field is a news Data Access Object that have a method to access directly to news database
     */
    @Autowired
    NewsDao newsDao;

    /**
     * This method is used to get news from News Database
     * to get individual news it will require an existed
     * news if from news database
     * @param id the id of existed news from news Database
     * @exception NewsNotFoundException
     * @return individual news from news database
     * */
    @Override
    public News getContent(String id) {
        News news = newsDao.getContent(id);
        if(news == null) throw new NewsNotFoundException(id);
        return news;
    }

    /**
     * This method is used to get all news from News Database
     * with require a Pagination format that require page number and size of element
     * @param page pagination format
     * @return all the news from News Database in pagination format
     * */
    @Override
    public Page<News> getAllContents(PageRequest page) {
        return newsDao.getAllContents(page);
    }

    /**
     * This method is used to get all news from News Database
     * @return all the news from News Database as List
     * */
    @Override
    public List<News> getAllContents(){
        return newsDao.getAllContents();
    }

    /**
     * This method is used to delete specific news using news id
     * the method will be fetching the news to make sure if news is already existed
     * after that it will delete the news
     * @param id the id of news that going to be deleted
     * @return the deleted news
     */
    @Override
    public News deleteContent(String id) {
        News news = newsDao.getContent(id);
        newsDao.deleteContent(id);
        return news;
    }

    /**
     * this method used to get all Empty alternate text from News Database
     * when this method called, it will create and empty list that will collect empty alternate text news
     * after that, method will search through each individual news and check if news have an empty alternate text
     * when the method check if news have empty alternate text it will add to the list
     * after that finding empty alternate text ended, the method will push the list in to pagination format
     * and return list of news that had empty alternate text as pagination format
     * @param page pagination format
     * @return list of news that had empty alternate text as pagination format
     */
    @Override
    public Page<News> getAllEmptyAltNews(PageRequest page) {
        List<News> emptyAlt = new ArrayList<>();
        for (News news: newsDao.getAllContents()) {
            if(news.getImages() == null) {
                continue;
            }
            for(Image img : news.getImages()) {
                if(img.getAlt().length() == 0){
                    emptyAlt.add(news);
                    break;
                }
            }
        }
        final int start = (int)page.getOffset();
        final int end = Math.min((start + page.getPageSize()), emptyAlt.size());
        return new PageImpl<>(emptyAlt.subList(start,end),page,emptyAlt.size());
    }

    /**
     * this method used to update the image alternated text by choosing the specific news id
     * when this method called, it will fetch the news by given id
     * then it will set the image alternate text value index by index
     * after the update process finished it will return the updated news
     * @param id the id of specific news
     * @param ImageText list of input of alternate text from user
     * @return news that alternate text of news images are updated
     */
    @Override
    public News updateImageContent(String id, List<Image> ImageText) {
        News news = newsDao.getContent(id);
        for(int i=0;i<ImageText.size();i++){
            news.getImages().get(i).setAlt(ImageText.get(i).getAlt());
        }
        return newsDao.updateContent(news);
    }

    /**
     * this method used to search the news by given title
     * when this method called, it will search news that contain a given title
     * after the searching process finish, it will return the matched news in pagination format
     * @param title query of word that going to search news title
     * @param page pagination format
     * @return list of news that matched query by given title in pagination format
     */
    @Override
    public Page<News> searchNews(String title,PageRequest page) {
        return newsDao.searchContent(title,page);
    }

    /**
     * this method used to fetch the news by given source
     * when this method called, it will fetch the news that have exact given source
     * after the fetch finish, it will return the fetch news in pagination format
     * @param source source of news Thairath, Sanook, Dek-D
     * @param page pagination format
     * @return list of news that fetched by given source in pagination format
     */
    @Override
    public Page<News> getNewsBySource(String source, PageRequest page) {
        return newsDao.getBySource(source,page);
    }
}
