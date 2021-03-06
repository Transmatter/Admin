package transmatter.platform.administration.content.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentDto {
    String id;
    String source;
    String author;
    String public_date;
    String title;
    String content;
    List<ImageDto> images;
    List<CommentDto> comment;
}
