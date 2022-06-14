package transmatter.platform.administration.security.service;

import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import transmatter.platform.administration.email.service.EmailService;
import transmatter.platform.administration.security.controller.JwtAuthenticationRequest;
import transmatter.platform.administration.security.dao.AdminDao;
import transmatter.platform.administration.security.entity.*;
import transmatter.platform.administration.security.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import transmatter.platform.administration.utils.SimpleUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    EmailService emailService;

    @Override
    public Page<Admin> getUnVerifyAdmin(PageRequest page) {
        List<Admin> admins = adminDao.getAllUser();
        List<Admin> unVerifyAdmin = new ArrayList<>();
        for(Admin admin : admins) {
            if(admin.getStatus() == VerifyStatus.NOT_VERIFIED) {
                unVerifyAdmin.add(admin);
            }
        }
        final int start = (int)page.getOffset();
        final int end = Math.min((start + page.getPageSize()), unVerifyAdmin.size());
        return new PageImpl<>(unVerifyAdmin.subList(start,end),page, unVerifyAdmin.size());
    }

    @Override
    public List<Admin> getAllUser() {
        return adminDao.getAllUser();
    }

    @Override
    public Admin getAdmin(Long id) {
        return adminDao.getAdmin(id);
    }

    @Override
    @SneakyThrows
    public Admin updateAdmin(Long id, Admin authenticationRequest) {
        Admin admin = adminDao.getAdmin(id);
        SimpleUtils.clone(authenticationRequest, admin,"password","status");
        return adminDao.updateAdmin(admin);
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminDao.getAdminByUsername(username);
    }

    @Override
    public Admin addUser(
            JwtAuthenticationRequest authenticationRequest
    ) {
        Authority authUser = Authority.builder().name(AuthorityName.ROLE_ADMIN).build();
        authorityRepository.save(authUser);

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        Admin admin = new Admin();
        admin.setEmail(authenticationRequest.getEmail());
        admin.setUsername(authenticationRequest.getUsername());
        admin.setPassword(encoder.encode(authenticationRequest.getPassword()));
        admin.setFirstname(authenticationRequest.getFirstname());
        admin.setLastname(authenticationRequest.getLastname());
        admin.setPhoneNo(authenticationRequest.getPhoneNo());

        admin.setEnabled(true);
        admin.getAuthorities().add(authUser);

        return adminDao.addUser(admin);
    }

    @Override
    @SneakyThrows
    public Admin verifyAdmin(String status, String reason, Long id){
        VerifyStatus stat = VerifyStatus.NA;
        Admin admin = adminDao.getAdmin(id);
        if(status.equals("VERIFIED")){
            stat = VerifyStatus.VERIFIED;
            emailService.sendVerifyMail(admin.getEmail());
        }
        if(status.equals("NOT_VERIFIED")){
            stat = VerifyStatus.NOT_VERIFIED;
            emailService.sendUnVerifyMail(admin.getEmail(),reason);
        }
        admin.setStatus(stat);
        return adminDao.updateAdmin(admin);
    }

    @Override
    public Boolean userValidation(JwtAuthenticationRequest authenticationRequest){
        List<Admin> admins = adminDao.getAllUser();
        if(authenticationRequest.getEmail() == null){
            for(Admin u : admins){
                if(u.getUsername().equals(authenticationRequest.getUsername())){
                    return false;
                }
            }
            return true;
        }

        // normal case
        for(Admin u : admins){
            if(
                    u.getUsername().equals(authenticationRequest.getUsername())
            ){
                return false;
            }
        }
        return true;
    }
}
