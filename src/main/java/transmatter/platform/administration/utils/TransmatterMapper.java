package transmatter.sandbox.content.utils;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import transmatter.sandbox.content.news.dto.NewsDto;
import transmatter.sandbox.content.news.entity.News;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface TransmatterMapper {
    TransmatterMapper INSTANCE = Mappers.getMapper(TransmatterMapper.class);

    NewsDto getNewsDto(News news);
    List<NewsDto> getNewsDto(List<News> news);
}
