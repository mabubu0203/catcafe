package mabubu0203.com.github.catcafe.api.components.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Headers {
    X_API_KEY("X-API-KEY"),
    ;

    private String key;
}
