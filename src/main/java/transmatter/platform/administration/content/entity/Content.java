package transmatter.platform.administration.content.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

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
    String publicDate;
    String title;
    String content;
    String category;
    List<Image> images;
    List<Comment> comment;

    @Builder.Default
    ContentStatus approveStatus = ContentStatus.NA;

    @Builder.Default
    ContentType type = ContentType.NA;

    @Nullable
    String approvedDate;

    @Nullable
    String approvedBy;
}
