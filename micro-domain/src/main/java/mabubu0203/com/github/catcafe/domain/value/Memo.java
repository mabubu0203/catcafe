package mabubu0203.com.github.catcafe.domain.value;

import java.util.Optional;

/**
 * メモ
 */
public record Memo(String value) {

  public boolean isEmpty() {
    return Optional.ofNullable(value).isEmpty();
  }

}
