package transmatter.platform.administration.security.service;

import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import transmatter.platform.administration.email.service.EmailService;
import transmatter.platform.administration.security.controller.JwtAuthenticationRequest;
import transmatter.platform.administration.security.dao.UserDao;
import transmatter.platform.administration.security.entity.*;
import transmatter.platform.administration.security.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import transmatter.platform.administration.utils.SimpleUtils;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    EmailService emailService;

    @Override
    public Page<User> getUnVerifyAdmin(PageRequest page) {
        List<User> users = userDao.getAllUser();
        List<User> unVerifyUser = new ArrayList<>();
        for(User user : users) {
            if(user.getStatus() == VerifyStatus.NOT_VERIFIED) {
                unVerifyUser.add(user);
            }
        }
        final int start = (int)page.getOffset();
        final int end = Math.min((start + page.getPageSize()), unVerifyUser.size());
        return new PageImpl<>(unVerifyUser.subList(start,end),page,unVerifyUser.size());
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public User getUser(Long userID) {
        return userDao.getUser(userID);
    }

    @Override
    @SneakyThrows
    public User updateUser(Long userID, User authenticationRequest) {
        User user = userDao.getUser(userID);
        SimpleUtils.clone(authenticationRequest,user,"password","status");
        return userDao.updateUser(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public User addUser(
            JwtAuthenticationRequest authenticationRequest
    ) {
        Authority authUser = Authority.builder().name(AuthorityName.ROLE_ADMIN).build();
        authorityRepository.save(authUser);

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setEmail(authenticationRequest.getEmail());
        user.setUsername(authenticationRequest.getUsername());
        user.setPassword(encoder.encode(authenticationRequest.getPassword()));
        user.setImageProfile(authenticationRequest.getImageProfile());
        user.setFirstname(authenticationRequest.getFirstname());
        user.setLastname(authenticationRequest.getLastname());
        user.setPhoneNo(authenticationRequest.getPhoneNo());

        user.setEnabled(true);
        user.getAuthorities().add(authUser);

        return userDao.addUser(user);
    }

    @Override
    @SneakyThrows
    public User verifyUser(String status,String reason, Long id){
        VerifyStatus stat = VerifyStatus.NA;
        User user = userDao.getUser(id);
        if(status.equals("VERIFIED")){
            stat = VerifyStatus.VERIFIED;
            emailService.sendVerifyMail(user.getEmail());
        }
        if(status.equals("NOT_VERIFIED")){
            stat = VerifyStatus.NOT_VERIFIED;
            emailService.sendUnVerifyMail(user.getEmail(),reason);
        }
        user.setStatus(stat);
        return userDao.updateUser(user);
    }

    @Override
    public Boolean userValidation(JwtAuthenticationRequest authenticationRequest){
        List<User> users = userDao.getAllUser();
        if(authenticationRequest.getEmail() == null){
            for(User u : users){
                if(u.getUsername().equals(authenticationRequest.getUsername())){
                    return false;
                }
            }
            return true;
        }

        // normal case
        for(User u : users){
            if(
                    u.getUsername().equals(authenticationRequest.getUsername())
            ){
                return false;
            }
        }
        return true;
    }
}
