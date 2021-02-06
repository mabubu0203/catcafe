package mabubu0203.com.github.catcafe.api.components.security;

import org.springframework.security.core.AuthenticationException;

// TODO:名前、パッケージ
public class HeaderNotFoundException extends AuthenticationException {
    public HeaderNotFoundException(String msg) {
        super(msg);
    }
}
