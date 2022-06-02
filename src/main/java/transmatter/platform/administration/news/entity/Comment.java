package transmatter.platform.administration.news.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    String author;
    String content;
    String time;
    List<String> image_urls = new ArrayList<>();
}
