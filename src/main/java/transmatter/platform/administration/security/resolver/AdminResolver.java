package transmatter.platform.administration.security.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import transmatter.platform.administration.security.dto.AdminDto;
import transmatter.platform.administration.security.entity.Admin;
import transmatter.platform.administration.security.service.AdminService;
import transmatter.platform.administration.utils.PageFilter;
import transmatter.platform.administration.utils.TransmatterMapper;

import javax.transaction.Transactional;

@Component
public class AdminResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    @Autowired
    AdminService adminService;

    @Transactional
    AdminDto getAdminById(Long id) {
        return TransmatterMapper.INSTANCE.getAdminDto(adminService.getAdmin(id));
    }

    @Transactional
    @SneakyThrows
    AdminDto updateAdmin(Long id, Admin admin) {
        return TransmatterMapper.INSTANCE.getAdminDto(adminService.updateAdmin(id, admin));
    }

    @Transactional
    @SneakyThrows
    AdminDto verifyAdmin(Long id, String reason, String status) {
        return TransmatterMapper.INSTANCE.getAdminDto(adminService.verifyAdmin(status,reason,id));
    }

    @Transactional
    Page<Admin> getUnVerifyAdmin(PageFilter filter) {
        return adminService.getUnVerifyAdmin(PageRequest.of(filter.getPage()-1,filter.getSize()));
    }
}
