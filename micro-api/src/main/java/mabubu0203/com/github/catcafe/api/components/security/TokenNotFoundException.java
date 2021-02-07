package mabubu0203.com.github.catcafe.api.components.security;

import org.springframework.security.core.AuthenticationException;

// TODO:名前、パッケージ
public class TokenNotFoundException extends AuthenticationException {

  public TokenNotFoundException(String msg) {
    super(msg);
  }
}
