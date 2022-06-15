package transmatter.platform.administration.security.dao;

import org.springframework.data.domain.Page;
import transmatter.platform.administration.security.entity.Admin;

import java.util.List;

public interface AdminDao {
    List<Admin> getAllUser();
    Page<Admin> getAllUserPagination(Integer pageSize, Integer pageNo);
    Admin getAdmin(Long userID);
    Admin updateAdmin(Admin admin);
    Admin getAdminByUsername(String username);
    Admin addUser(Admin admin);
}
