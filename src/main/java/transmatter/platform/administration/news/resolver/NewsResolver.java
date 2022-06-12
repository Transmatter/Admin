package transmatter.platform.administration.news.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import transmatter.platform.administration.news.dto.NewsDto;
import transmatter.platform.administration.news.entity.Image;
import transmatter.platform.administration.news.entity.News;
import transmatter.platform.administration.news.service.NewsService;
import transmatter.platform.administration.utils.PageFilter;
import transmatter.platform.administration.utils.TransmatterMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    /**
     * An instance of NewsService used for calling method form NewsService
     */
    @Autowired
    NewsService newsService;

    /**
     * This methods used to get specific news by given news id
     * @param id id of existed news in news database
     * @return specific news content
     */
    @Transactional
    News getContent(String id){
        return newsService.getContent(id);
    }

    /**
     * This method used to get all news from news database in pagination format
     * by passing page filter as parameter
     * @param filter a page filter that have attribute page number (page) and amount of element in a page (size)
     * @return list of news in pagination format
     */
    @Transactional
    Page<News> getAllContents(PageFilter filter){
        return newsService.getAllContents(PageRequest.of(filter.getPage()-1,filter.getSize()));
    }

    /**
     * This method used to delete news by given specific id
     * @param id id of specific news that aim to be delete
     * @return the news that deleted by this method
     */
    @Transactional
    News deleteContent(String id){
        return newsService.deleteContent(id);
    }

    /**
     * this method used to get all news that had empty image alternate text in pagination format
     * @param filter a page filter that have attribute page number (page) and amount of element in a page (size)
     * @return list of news that had empty image alternate text in pagination format
     */
    @Transactional
    Page<News> getAllEmptyAltNews(PageFilter filter) {
        return newsService.getAllEmptyAltNews(PageRequest.of(filter.getPage()-1,filter.getSize()));
    }

    /**
     * this method used to update images alternate text in specific news by given id
     * @param id id of specific news that aim to be updated
     * @param imageText list of image alternate text
     * @return the news that updated by this method
     */
    @Transactional
    News updateImageContent(String id,List<Image> imageText){
        return newsService.updateImageContent(id,imageText);
    }

    /**
     * this method used to get all news that match news source in pagination format
     * @param source source of news e.g. Thairath, Sanook, Dek-D
     * @param filter a page filter that have attribute page number (page) and amount of element in a page (size)
     * @return list of news that match by source in pagination format
     */
    @Transactional
    Page<News> getNewsBySource(String source,PageFilter filter) {
        return newsService.getNewsBySource(source,PageRequest.of(filter.getPage()-1, filter.getSize()));
    }

    /**
     * this method used to get all news that contain query (title) and return it in pagination format
     * @param title query for news searching
     * @param filter a page filter that have attribute page number (page) and amount of element in a page (size)
     * @return list of news that contain a title in pagination format
     */
    Page<News> searchNews(String title, PageFilter filter) {
        return newsService.searchNews(title,PageRequest.of(filter.getPage()-1,filter.getSize()));
    }
}
