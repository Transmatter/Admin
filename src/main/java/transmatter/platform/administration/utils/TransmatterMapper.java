package transmatter.platform.administration.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import transmatter.platform.administration.news.dto.NewsDto;
import transmatter.platform.administration.news.entity.News;
import transmatter.platform.administration.security.dto.UserAuthDto;
import transmatter.platform.administration.security.dto.UserDto;
import transmatter.platform.administration.security.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface TransmatterMapper {
    TransmatterMapper INSTANCE = Mappers.getMapper(TransmatterMapper.class);

    NewsDto getNewsDto(News news);
    List<NewsDto> getNewsDto(List<News> news);

    UserDto getUserDto(User user);
    List<UserDto> getUserDto(List<User> user);

    @Mapping(target = "authorities", expression = "java(user.getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    UserAuthDto getUserAuthDto(User user);
}
