package transmatter.platform.administration.content.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import transmatter.platform.administration.content.dao.ContentDao;
import transmatter.platform.administration.content.entity.*;
import transmatter.platform.administration.security.dao.AdminDao;
import transmatter.platform.administration.security.entity.Admin;
import transmatter.platform.administration.utils.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {
    final ContentDao contentDao;
    final JwtTokenUtil jwtTokenUtil;
    final AdminDao adminDao;
    // ================ progress 1 ================================

    @Override
    public Content getContent(String id) {
        return contentDao.getContent(id);
    }

    @Override
    public Page<Content> getAllContents(PageRequest page) {
        return contentDao.getAllContents(page);
    }

    @Override
    public Content deleteContent(String id) {
        Content content = contentDao.getContent(id);
        contentDao.deleteContent(id);
        return content;
    }

    @Override
    public Page<Content> searchNews(String title, PageRequest page) {
        return contentDao.searchContent(title,page);
    }

    @Override
    public Page<Content> getNewsBySourceAndType(String source, String type, PageRequest page) {
        if(type.equals("ทั้งหมด")){
            return contentDao.getBySource(source,page);
        }
        return contentDao.getBySourceAndType(source,type,page);
    }
    // ================ progress 1 =================================

    // ================ progress 2 admin part ======================

    @Override
    public Content updateImageContent(String id, List<Image> ImageText, HttpServletRequest header) {
        Content content = contentDao.getContent(id);
        Admin admin = getAdmin(header);
        String adminName = admin.getFirstname() + " " + admin.getLastname();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatter.format(new Date());
        for(int i=0;i<content.getImages().size();i++){
            if(ImageText.get(i).getAlt() != null){
                content.getImages().get(i).setAlt(ImageText.get(i).getAlt());
                content.getImages().get(i).setVerifyStatus(ImageStatus.COMPLETE);
                content.getImages().get(i).setVerifiedBy(adminName);
                content.getImages().get(i).setVerifiedDate(date);
            }
        }
        if(checkContentImage(content)){
            content.setApproveStatus(ContentStatus.APPROVE);
            content.setApprovedBy(adminName);
            content.setApprovedDate(date);
        }
        return contentDao.updateContent(content);
    }

    private Boolean checkContentImage(Content content){
        for(Image image : content.getImages()){
            if(image.getVerifyStatus() != ImageStatus.COMPLETE){
                return false;
            }
        }
        return true;
    }

    private Admin getAdmin(HttpServletRequest header){
        String token = header.getHeader("Authorization");
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return adminDao.getAdminByUsername(username);
    }

    @Override
    public Content updateContent(String id, String title, String text) {
        Content content = contentDao.getContent(id);
        content.setTitle(title);
        content.setContent(text);
        return contentDao.updateContent(content);
    }

    @Override
    public Page<Content> getAllEmptyAltNews(PageRequest page) {
        return contentDao.getAllEmptyAltNews(page);
    }

    @Override
    public Page<Content> getContentByDate(String start, String end, PageRequest page) {
        return contentDao.getContentByDate(start,end,page);
    }

    @Override
    public Page<Content> getContentByType(ContentType type, PageRequest page) {
        return contentDao.getContentType(type,page);
    }

    // ================== progress 2 vi part ======================

    @Override
    public Page<Content> getAllApproveContent(PageRequest page) {
        return contentDao.getAllApproveContent(page);
    }

    @Override
    public Page<Content> getApproveContentByDate(String start, String end, PageRequest page) {
        return contentDao.getApproveContentByDate(start,end,page);
    }

    @Override
    public Page<Content> searchOnlyApproveContent(String title, PageRequest page) {
        return contentDao.searchOnlyApproveContent(title,page);
    }

    @Override
    public Page<Content> getOnlyApproveContentBySource(String source, String type, PageRequest page) {
        if(type.equals("ทั้งหมด")){
            return contentDao.getApproveContentBySource(source,page);
        }
        return contentDao.getOnlyApproveContentBySource(source,type,page);
    }

    // ================ progress 2 ======================
}
