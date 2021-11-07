package mabubu0203.com.github.catcafe.domain.value;

import lombok.Getter;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.domain.check.CheckPhoneNumber;


@Accessors(fluent = true)
@Getter
public class PhoneNumber {

  @CheckPhoneNumber
  private final String value;

  public PhoneNumber(String value) {
    this.value = value;
  }

}
