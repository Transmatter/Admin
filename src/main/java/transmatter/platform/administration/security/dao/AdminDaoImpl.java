package transmatter.platform.administration.security.dao;

import transmatter.platform.administration.security.entity.Admin;
import transmatter.platform.administration.security.repository.AuthorityRepository;
import transmatter.platform.administration.security.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDaoImpl implements AdminDao {
    @Autowired
    AdminRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public List<Admin> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Page<Admin> getAllUserPagination(Integer pageSize, Integer pageNo) {
        return userRepository.findAll(PageRequest.of(pageNo-1,pageSize));
    }

    @Override
    public Admin getAdmin(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        return userRepository.save(admin);
    }

    @Override
    public Admin getAdminByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public Admin addUser(Admin admin){
        return userRepository.save(admin);
    }
}
