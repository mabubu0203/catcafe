package mabubu0203.com.github.catcafe.domain.value;

import java.util.Optional;

/**
 * 店舗住所(郵便番号)
 */
public record PostalCode(String value) {

  public boolean isEmpty() {
    return Optional.ofNullable(value).isEmpty();
  }

}
