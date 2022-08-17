package transmatter.platform.administration.content.resolver;

import graphql.kickstart.servlet.context.GraphQLServletContext;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import transmatter.platform.administration.content.entity.Content;
import transmatter.platform.administration.content.entity.ContentRequest;
import transmatter.platform.administration.content.entity.ContentType;
import transmatter.platform.administration.content.entity.Image;
import transmatter.platform.administration.content.service.ContentService;
import transmatter.platform.administration.utils.PageFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    Page<Content> searchNews(String title, PageFilter filter) {
        return contentService.searchNews(title,PageRequest.of(filter.getPage()-1,filter.getSize()));
    }

    Page<Content> getNewsBySourceAndType(String source, String type, PageFilter filter){
        return contentService.getNewsBySourceAndType(source,type,PageRequest.of(filter.getPage()-1,filter.getSize()));
    }


    @Transactional
    Page<Content> getAllEmptyAltNews(PageFilter filter) {
        return contentService.getAllEmptyAltNews(PageRequest.of(filter.getPage()-1,filter.getSize()));
    }

    Content updateImageContent(String id, List<Image> imageText, DataFetchingEnvironment env) {
        GraphQLServletContext context = env.getContext();
        HttpServletRequest request = context.getHttpServletRequest();
        return contentService.updateImageContent(id,imageText,request);
    }

    Content updateContent(String id, ContentRequest content) {
        return contentService.updateContent(id,content.getTitle(),content.getContent());
    }

    Page<Content> getContentByDate(String start, String end, PageFilter filter) {
        return contentService.getContentByDate(start,end,PageRequest.of(filter.getPage()-1,filter.getSize()));
    }

    Page<Content> getContentByType(String type, PageFilter filter) {
        return contentService.getContentByType(ContentType.valueOf(type),PageRequest.of(filter.getPage()-1,filter.getSize()));
    }

    // ============================ vi ============================

    Page<Content> getAllApprovedContent(PageFilter filter) {
        return contentService.getAllApproveContent(PageRequest.of(filter.getPage()-1,filter.getSize()));
    }

    Page<Content> getAllApprovedContentByDate(String start, String end, PageFilter filter) {
        return contentService.getApproveContentByDate(start,end,PageRequest.of(filter.getPage()-1,filter.getSize()));
    }

    Page<Content> getOnlyApprovedContentBySource(String source, String category, PageFilter filter) {
        return contentService.getOnlyApproveContentBySource(source,category,PageRequest.of(filter.getPage()-1,filter.getSize()));
    }

    Page<Content> searchOnlyApprovedContent(String title, PageFilter filter) {
        return contentService.searchOnlyApproveContent(title,PageRequest.of(filter.getPage()-1,filter.getSize()));
    }
}
