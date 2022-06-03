package transmatter.platform.administration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import transmatter.platform.administration.security.entity.Authority;
import transmatter.platform.administration.security.entity.AuthorityName;
import transmatter.platform.administration.security.entity.User;
import transmatter.platform.administration.security.repository.AuthorityRepository;
import transmatter.platform.administration.security.repository.UserRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent>  {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    User admin1, admin2;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        addAdmin();
    }

    private void addAdmin(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authUser = Authority.builder().name(AuthorityName.ROLE_USER).build();
        authorityRepository.save(authUser);
        userRepository.save(
                User.builder()
                        .authorities(List.of(authUser))
                        .email("oat431@gmail.com")
                        .firstname("Sahachan")
                        .lastname("Tippimwong")
                        .password(encoder.encode("user+123"))
                        .username("oat431")
                        .phoneNo("0836306462")
                        .enabled(true)
                        .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .build()
        );
        userRepository.save(
                User.builder()
                        .authorities(List.of(authUser))
                        .email("transmatter.team@gmail.com")
                        .firstname("Transmatter")
                        .lastname("Admin")
                        .password(encoder.encode("TMAT-123"))
                        .username("tmat111")
                        .phoneNo("0987654321")
                        .enabled(true)
                        .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .build()
        );
    }
}
