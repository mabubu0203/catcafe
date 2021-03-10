package mabubu0203.com.github.catcafe.common.exception;

import org.springframework.security.core.AuthenticationException;

public class HeaderNotFoundException extends AuthenticationException {

  public HeaderNotFoundException(String msg) {
    super(msg);
  }
}
