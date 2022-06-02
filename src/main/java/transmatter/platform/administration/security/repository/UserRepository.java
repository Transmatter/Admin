package transmatter.platform.administration.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transmatter.platform.administration.security.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
