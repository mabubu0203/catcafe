package mabubu0203.com.github.catcafe.api.components.security;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorizedInformation {

  private String accessToken;
  private LocalDateTime expires;
  private String[] authorities;

}
