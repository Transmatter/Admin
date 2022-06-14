package transmatter.platform.administration.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import transmatter.platform.administration.security.entity.VerifyStatus;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminAuthDto {
    Long id;
    String username;
    VerifyStatus status;
    List<String> authorities;
}
