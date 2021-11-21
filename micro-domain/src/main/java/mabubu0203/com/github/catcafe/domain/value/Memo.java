package mabubu0203.com.github.catcafe.domain.value;

import java.util.Optional;

public record Memo(String value) {

  public boolean isEmpty() {
    return Optional.of(value).isEmpty();
  }

}
