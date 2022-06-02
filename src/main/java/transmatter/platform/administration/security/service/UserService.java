package transmatter.platform.administration.security.service;

import transmatter.platform.administration.security.controller.JwtAuthenticationRequest;
import transmatter.platform.administration.security.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User getUser(Long userID);
    User updateUser(Long userID,JwtAuthenticationRequest user);
    User getUserByUsername(String username);
    Boolean userValidation(JwtAuthenticationRequest authenticationRequest);
    User addUser(JwtAuthenticationRequest authenticationRequest);
}
