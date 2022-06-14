package transmatter.platform.administration.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import transmatter.platform.administration.news.dto.NewsDto;
import transmatter.platform.administration.news.entity.News;
import transmatter.platform.administration.security.dto.AdminAuthDto;
import transmatter.platform.administration.security.dto.AdminDto;
import transmatter.platform.administration.security.entity.Admin;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface TransmatterMapper {
    TransmatterMapper INSTANCE = Mappers.getMapper(TransmatterMapper.class);

    NewsDto getNewsDto(News news);
    List<NewsDto> getNewsDto(List<News> news);

    @Mapping(target = "status",expression = "java( admin.getStatus().toString())")
    AdminDto getAdminDto(Admin admin);
    List<AdminDto> getAdminDto(List<Admin> admin);

    @Mapping(target = "authorities", expression = "java(admin.getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    AdminAuthDto getAdminAuthDto(Admin admin);
}
