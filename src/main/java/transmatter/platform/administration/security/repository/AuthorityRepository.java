package transmatter.platform.administration.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import transmatter.platform.administration.security.entity.Authority;
import transmatter.platform.administration.security.entity.AuthorityName;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(AuthorityName input);
}
