package transmatter.platform.administration.email.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import transmatter.platform.administration.email.entity.Email;
import transmatter.platform.administration.email.service.EmailService;

@Controller
public class EmailController {
    @Autowired
    EmailService emailService;
    @PostMapping(value = "/send-email")
    @SneakyThrows
    public ResponseEntity<?> sendEmail(
            @RequestBody Email email
    ){
        emailService.sendMail(email.getEmail());
        return ResponseEntity.ok("Email was sent");
    }
}
