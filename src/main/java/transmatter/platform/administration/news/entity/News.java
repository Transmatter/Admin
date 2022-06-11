package transmatter.platform.administration.news.entity;

import com.mysema.query.annotations.QueryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@QueryEntity
@Document
public class News {
    /** id of news */
    @Id
    String id;

    /** source of news e.g. thairath, sanook, dek-d */
    String source;

    /** author of mews */
    String author;

    /** date of public news */
    String public_date;

    /** title of news */
    String title;

    /** news content */
    String content;

    /** category of news */
    String type;

    /** set of images in news */
    List<Image> images;

    /** set of comment in news */
    List<Comment> comment;
}
