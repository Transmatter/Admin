package transmatter.platform.administration.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transmatter.platform.administration.security.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
    Admin findByEmail(String email);
}
