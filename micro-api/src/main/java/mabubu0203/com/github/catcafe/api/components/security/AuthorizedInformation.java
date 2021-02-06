package mabubu0203.com.github.catcafe.api.components.security;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AuthorizedInformation {

    private String accessToken;
    private LocalDateTime expires;
    private String[] authorities;

}
