package transmatter.platform.administration.content.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import transmatter.platform.administration.content.entity.Content;
import transmatter.platform.administration.content.repository.ContentRepository;

import java.util.List;

@Repository
public class ContentDaoImpl implements ContentDao {
    @Autowired
    ContentRepository contentRepository;

    @Override
    public Content getContent(String id) {
        return contentRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Content> getAllContents(PageRequest pageRequest) {
        return contentRepository.findAll(pageRequest);
    }

    @Override
    public List<Content> getAllContents(){
        return contentRepository.findAll();
    }

    @Override
    public void deleteContent(String id) {
        contentRepository.deleteById(id);
    }

//    @Override
//    public News updateContent(News news) {
//        return newsRepository.save(news);
//    }

    @Override
    public Page<Content> searchContent(String title, PageRequest page) {
        return contentRepository.findByTitleContaining(title,page);
    }

    @Override
    public Page<Content> getBySource(String source, PageRequest page) {
        return contentRepository.findBySource(source,page);
    }

    @Override
    public Page<Content> getBySourceAndType(String source, String type, PageRequest page) {
        return contentRepository.findBySourceAndType(source,type,page);
    }
}
