package mabubu0203.com.github.catcafe.api.components.security;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AuthorizedInformation {

    private String accessToken;
    private LocalDate expires;
    private String[] authorities;

}
