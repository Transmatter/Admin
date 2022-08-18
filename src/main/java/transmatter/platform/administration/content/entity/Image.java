package transmatter.platform.administration.content.entity;

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

    @Builder.Default
    ImageStatus verifyStatus = ImageStatus.NA;

    @Nullable
    String verifiedBy;

    @Nullable
    String verifiedDate;
}
