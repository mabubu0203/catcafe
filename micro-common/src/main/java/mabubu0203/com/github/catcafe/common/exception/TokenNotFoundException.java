package mabubu0203.com.github.catcafe.common.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenNotFoundException extends AuthenticationException {

  public TokenNotFoundException(String msg) {
    super(msg);
  }
}
