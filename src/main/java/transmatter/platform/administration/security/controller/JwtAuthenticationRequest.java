package transmatter.platform.administration.security.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by stephan on 20.03.16.
 */
@Data
@Builder
@AllArgsConstructor
public class JwtAuthenticationRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -8445943548965154778L;

    private String firstname;
    private String lastname;
    private String email;
    private String phoneNo;
    private String username;
    private String password;
}
