package transmatter.platform.administration.security.controller;

import org.springframework.data.mongodb.util.BsonUtils;
import transmatter.platform.administration.email.service.EmailService;
import transmatter.platform.administration.security.entity.Admin;
import transmatter.platform.administration.security.repository.AuthorityRepository;
import transmatter.platform.administration.security.service.AdminService;
import transmatter.platform.administration.utils.JwtTokenUtil;
import transmatter.platform.administration.utils.TransmatterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import transmatter.platform.administration.security.entity.JwtUser;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    AdminService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    AuthorityRepository authorityRepository;

    @PostMapping("${jwt.route.authentication.path}")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails, device);
        Map result = new HashMap();
        result.put("token", token);
        Admin admin = userService.getAdminByUsername(userDetails.getUsername());
        if (admin != null) {
            result.put("user", TransmatterMapper.INSTANCE.getAdminAuthDto(admin));
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "${jwt.route.authentication.refresh}")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("${jwt.route.register.path}")
    public ResponseEntity<?> addUser(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device) throws AuthenticationException, MessagingException, IOException {
        Admin admin = new Admin();
        HttpHeaders responseHeader = new HttpHeaders();
        if(!userService.userValidation(authenticationRequest)){
            return new ResponseEntity<>(
                    "The Email, PersonalID, or username was taken by other user",
                    responseHeader,
                    HttpStatus.BAD_REQUEST
            );
        } else {
            admin = userService.addUser(authenticationRequest);
            emailService.sendMail(admin.getEmail());
            return ResponseEntity.ok(TransmatterMapper.INSTANCE.getAdminDto(admin));
        }

    }

}
