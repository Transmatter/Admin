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
    @Autowired
    NewsService newsService;

    @Transactional
    News getContent(String id){
        return newsService.getContent(id);
    }

    @Transactional
    Page<News> getAllContents(PageFilter filter){
        return newsService.getAllContents(PageRequest.of(filter.getPage()-1,filter.getSize()));
    }

    @Transactional
    News deleteContent(String id){
        return newsService.deleteContent(id);
    }

    @Transactional
    Page<News> getAllEmptyAltNews(PageFilter filter) {
        return newsService.getAllEmptyAltNews(PageRequest.of(filter.getPage()-1,filter.getSize()));
    }

//    @Transactional
//    News updateImageContent(String id,List<Image> imageText){
//        return newsService.updateImageContent(id,imageText);
//    }

    @Transactional
    Page<News> getNewsBySource(String source,PageFilter filter) {
        return newsService.getNewsBySource(source,PageRequest.of(filter.getPage()-1, filter.getSize()));
    }

    Page<News> searchNews(String title, PageFilter filter) {
        return newsService.searchNews(title,PageRequest.of(filter.getPage()-1,filter.getSize()));
    }

    Page<News> getNewsBySourceAndType(String source, String type, PageFilter filter){
        return newsService.getNewsBySourceAndType(source,type,PageRequest.of(filter.getPage(),filter.getSize()));
    }
}
