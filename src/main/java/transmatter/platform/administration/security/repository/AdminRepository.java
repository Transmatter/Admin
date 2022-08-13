package transmatter.platform.administration.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import transmatter.platform.administration.security.entity.Admin;
import transmatter.platform.administration.security.entity.VerifyStatus;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
    Admin findByEmail(String email);
    List<Admin> findByStatus(VerifyStatus status);
    Page<Admin> findByStatus(VerifyStatus status, PageRequest pageRequest);
}
