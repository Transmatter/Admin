package transmatter.platform.administration.email.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.*;
import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService{

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    @SneakyThrows
    public void sendMail(String email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        String subject = "Transmatter team: Registration Link";
        helper.setSubject(subject);
        helper.setText("<h1>We Receive Your Registration data</h1><p>Please Wait our Admin to verify you data</p><p>regard. Transmatter Team</p>", true);
        helper.setTo(email);
        helper.setFrom(username);
        javaMailSender.send(mimeMessage);
    }

    @Override
    @SneakyThrows
    public void sendVerifyMail(String email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        String subject = "Transmatter team: Your Account had been verified";
        helper.setSubject(subject);
        helper.setText("<h1>Your Account had been verify</h1><p>you receive this email, because of you data has been verify by us.</p><p>now you can edit or filter our news and image to help our visual impaired</p>", true);
        helper.setTo(email);
        helper.setFrom(username);
        javaMailSender.send(mimeMessage);
    }

    @Override
    @SneakyThrows
    public void sendUnVerifyMail(String email,String reason) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        String subject = "Transmatter team: We have little problem about your account";
        helper.setSubject(subject);
        helper.setText("<h1>Your Account Didn't verify yet!</h1><p>you receive this email, because of you data has has some problem by following:</p><p>"+reason+"</p>"+"<p>please login to the platform and update your date</p>", true);
        helper.setTo(email);
        helper.setFrom(username);
        javaMailSender.send(mimeMessage);
    }
}
