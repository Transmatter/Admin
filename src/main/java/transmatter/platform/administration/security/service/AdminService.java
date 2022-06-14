package transmatter.platform.administration.security.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import transmatter.platform.administration.security.controller.JwtAuthenticationRequest;
import transmatter.platform.administration.security.entity.Admin;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface AdminService {
    Page<Admin> getUnVerifyAdmin(PageRequest page);
    List<Admin> getAllUser();
    Admin getAdmin(Long userID);
    Admin updateAdmin(Long userID, Admin admin) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException;
    Admin getAdminByUsername(String username);
    Boolean userValidation(JwtAuthenticationRequest authenticationRequest);
    Admin addUser(JwtAuthenticationRequest authenticationRequest);
    Admin verifyAdmin(String status, String reason, Long id) throws MessagingException, IOException;
}
