package transmatter.platform.administration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import transmatter.platform.administration.security.entity.Admin;
import transmatter.platform.administration.security.entity.Authority;
import transmatter.platform.administration.security.entity.AuthorityName;
import transmatter.platform.administration.security.entity.VerifyStatus;
import transmatter.platform.administration.security.repository.AuthorityRepository;
import transmatter.platform.administration.security.repository.AdminRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent>  {
    @Autowired
    AdminRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    Admin admin1, admin2;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        addAdmin();
    }

    private void addAdmin(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority admin = Authority.builder().name(AuthorityName.ROLE_ADMIN).build();
        Authority superAdmin = Authority.builder().name(AuthorityName.ROLE_SUPER_ADMIN).build();
        authorityRepository.save(admin);
        authorityRepository.save(superAdmin);
        userRepository.save(
                transmatter.platform.administration.security.entity.Admin.builder()
                        .authorities(List.of(admin))
                        .email("oat431@gmail.com")
                        .firstname("Sahachan")
                        .lastname("Tippimwong")
                        .password(encoder.encode("user+123"))
                        .username("oat431")
                        .phoneNo("0836306462")
                        .enabled(true)
                        .status(VerifyStatus.VERIFIED)
                        .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .build()
        );
        userRepository.save(
                transmatter.platform.administration.security.entity.Admin.builder()
                        .authorities(List.of(admin))
                        .email("sahachan_t@cmu.ac.th")
                        .firstname("Tippimwong")
                        .lastname("Sahachan")
                        .password(encoder.encode("user+321"))
                        .username("pot561")
                        .phoneNo("0836306461")
                        .enabled(true)
                        .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .build()
        );
        userRepository.save(
                Admin.builder()
                        .authorities(List.of(admin))
                        .email("pun321@gmail.com")
                        .firstname("Thitisan")
                        .lastname("Chiluek")
                        .password(encoder.encode("user+111"))
                        .username("pun321")
                        .phoneNo("0836306261")
                        .enabled(true)
                        .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .build()
        );
        userRepository.save(
                transmatter.platform.administration.security.entity.Admin.builder()
                        .authorities(List.of(superAdmin))
                        .email("transmatter.team@gmail.com")
                        .firstname("Transmatter")
                        .lastname("Admin")
                        .password(encoder.encode("TMAT-123"))
                        .username("tmat111")
                        .phoneNo("0987654321")
                        .enabled(true)
                        .status(VerifyStatus.VERIFIED)
                        .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .build()
        );
    }
}
