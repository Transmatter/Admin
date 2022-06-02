package transmatter.platform.administration.news.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Nullable
    String url;

    @Builder.Default
    String alt = "";
}
