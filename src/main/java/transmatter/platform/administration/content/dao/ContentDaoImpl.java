package transmatter.platform.administration.content.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import transmatter.platform.administration.content.entity.Content;
import transmatter.platform.administration.content.entity.ContentStatus;
import transmatter.platform.administration.content.entity.ContentType;
import transmatter.platform.administration.content.repository.ContentRepository;

import java.util.List;

@Repository
public class ContentDaoImpl implements ContentDao {
    @Autowired
    ContentRepository contentRepository;

    // =================== progress 1 ======================== //
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
        return contentRepository.findBySourceAndCategory(source,type,page);
    }

    // =========================== progress 2 admin part ========================= //

    @Override
    public Content updateContent(Content news) {
        return contentRepository.save(news);
    }

    @Override
    public Page<Content> getAllEmptyAltNews(PageRequest page) {
        return contentRepository.findByApproveStatus(ContentStatus.NOT_APPROVE,page);
    }

    @Override
    public Page<Content> getContentByDate(String start, String end, PageRequest page) {
        return contentRepository.findByPublicDateBetween(start,end,page);
    }

    @Override
    public Page<Content> getContentType(ContentType type, PageRequest page) {
        return contentRepository.findByType(type,page);
    }

    // ========================== progress 2 vi part =================================== //
    @Override
    public Page<Content> getAllApproveContent(PageRequest page) {
        return contentRepository.findByApproveStatus(ContentStatus.APPROVE,page);
    }

    @Override
    public Page<Content> getApproveContentByDate(String start, String end, PageRequest page) {
        return contentRepository.findByApprovedDateBetween(start,end,page);
    }

    @Override
    public Page<Content> searchOnlyApproveContent(String title, PageRequest page) {
        return contentRepository.findByTitleContainingAndApproveStatus(title,ContentStatus.APPROVE,page);
    }

    @Override
    public Page<Content> getOnlyApproveContentBySource(String source, String type, PageRequest page) {
        return contentRepository.findBySourceAndTypeAndApproveStatus(source,type,ContentStatus.APPROVE, page);
    }

    @Override
    public Page<Content> getApproveContentBySource(String source, PageRequest page) {
        return contentRepository.findBySourceAndApproveStatus(source,ContentStatus.APPROVE, page);
    }
}
