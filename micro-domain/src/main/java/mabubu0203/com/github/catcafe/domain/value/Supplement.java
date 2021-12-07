package mabubu0203.com.github.catcafe.domain.value;

import java.util.Optional;

/**
 * 補足
 */
public record Supplement(String value) {

  public boolean isEmpty() {
    return Optional.ofNullable(value).isEmpty();
  }

}
