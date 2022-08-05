package transmatter.platform.administration.content.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Content {
    @Id
    String id;

    String source;
    String author;
    String public_date;
    String title;
    String content;
    String type;
    List<Image> images;
    List<Comment> comment;
}
