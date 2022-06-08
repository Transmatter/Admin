package transmatter.platform.administration.security.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import transmatter.platform.administration.security.dto.UserDto;
import transmatter.platform.administration.security.entity.User;
import transmatter.platform.administration.security.service.UserService;
import transmatter.platform.administration.utils.TransmatterMapper;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    @Autowired
    UserService userService;

    @Transactional
    UserDto getUserByID(Long id) {
        return TransmatterMapper.INSTANCE.getUserDto(userService.getUser(id));
    }

    @Transactional
    @SneakyThrows
    UserDto updateUser(Long id, User user) {
        return TransmatterMapper.INSTANCE.getUserDto(userService.updateUser(id,user));
    }

    @Transactional
    @SneakyThrows
    UserDto verifyUser(Long id,String reason,String status) {
        return TransmatterMapper.INSTANCE.getUserDto(userService.verifyUser(status,reason,id));
    }

    @Transactional
    List<UserDto> getUnVerifyAdmin() {
        return TransmatterMapper.INSTANCE.getUserDto(userService.getUnVerifyAdmin());
    }
}
