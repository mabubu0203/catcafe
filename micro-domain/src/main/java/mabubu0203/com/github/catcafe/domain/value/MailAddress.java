package mabubu0203.com.github.catcafe.domain.value;

import java.util.Optional;

/**
 * メールアドレス
 */
public record MailAddress(String value) {

  public boolean isEmpty() {
    return Optional.ofNullable(value)
        .map(String::isEmpty)
        .orElse(true);
  }

}
