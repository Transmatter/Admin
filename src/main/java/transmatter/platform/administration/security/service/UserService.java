package transmatter.platform.administration.security.service;

import transmatter.platform.administration.security.controller.JwtAuthenticationRequest;
import transmatter.platform.administration.security.entity.User;
import transmatter.platform.administration.security.entity.VerifyStatus;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface UserService {
    List<User> getUnVerifyAdmin();
    List<User> getAllUser();
    User getUser(Long userID);
    User updateUser(Long userID,User user) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException;
    User getUserByUsername(String username);
    Boolean userValidation(JwtAuthenticationRequest authenticationRequest);
    User addUser(JwtAuthenticationRequest authenticationRequest);
    User verifyUser(String status,String reason, Long id) throws MessagingException, IOException;
}
