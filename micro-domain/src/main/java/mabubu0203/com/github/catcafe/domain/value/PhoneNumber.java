package mabubu0203.com.github.catcafe.domain.value;

import mabubu0203.com.github.catcafe.domain.check.CheckPhoneNumber;
import java.util.Optional;

public record PhoneNumber(@CheckPhoneNumber String value) {

  public boolean isEmpty() {
    return Optional.of(value).isEmpty();
  }

}
