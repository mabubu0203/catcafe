package mabubu0203.com.github.catcafe.domain.value;

import java.util.Optional;

public record XApiKeyToken(String value) {

  public boolean isEmpty() {
    return Optional.of(value).isEmpty();
  }

}
