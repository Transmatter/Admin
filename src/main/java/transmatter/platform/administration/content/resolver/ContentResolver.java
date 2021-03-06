package transmatter.platform.administration.content.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import transmatter.platform.administration.content.entity.Content;
import transmatter.platform.administration.content.service.ContentService;
import transmatter.platform.administration.utils.PageFilter;

@Component
public class ContentResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    @Autowired
    ContentService contentService;

    @Transactional
    Content getContent(String id){
        return contentService.getContent(id);
    }

    @Transactional
    Page<Content> getAllContents(PageFilter filter){
        return contentService.getAllContents(PageRequest.of(filter.getPage()-1,filter.getSize()));
    }

    @Transactional
    Content deleteContent(String id){
        return contentService.deleteContent(id);
    }

    @Transactional
    Page<Content> getAllEmptyAltNews(PageFilter filter) {
        return contentService.getAllEmptyAltNews(PageRequest.of(filter.getPage()-1,filter.getSize()));
    }

//    @Transactional
//    News updateImageContent(String id,List<Image> imageText){
//        return newsService.updateImageContent(id,imageText);
//    }

    @Transactional
    Page<Content> getNewsBySource(String source, PageFilter filter) {
        return contentService.getNewsBySource(source,PageRequest.of(filter.getPage()-1, filter.getSize()));
    }

    Page<Content> searchNews(String title, PageFilter filter) {
        return contentService.searchNews(title,PageRequest.of(filter.getPage()-1,filter.getSize()));
    }

    Page<Content> getNewsBySourceAndType(String source, String type, PageFilter filter){
        return contentService.getNewsBySourceAndType(source,type,PageRequest.of(filter.getPage(),filter.getSize()));
    }
}
