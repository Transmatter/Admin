package transmatter.sandbox.content.news.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import transmatter.sandbox.content.news.dto.NewsDto;
import transmatter.sandbox.content.news.entity.Image;
import transmatter.sandbox.content.news.entity.News;
import transmatter.sandbox.content.news.service.NewsService;
import transmatter.sandbox.content.utils.TransmatterMapper;

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
    List<News> getAllContents(){
        return newsService.getAllContents();
    }

    @Transactional
    News deleteContent(String id){
        return newsService.deleteContent(id);
    }

    List<News> getAllEmptyAltNews() {
        return newsService.getAllEmptyAltNews();
    }

    News updateImageContent(String id,List<Image> imageText){
        return newsService.updateImageContent(id,imageText);
    }
}
