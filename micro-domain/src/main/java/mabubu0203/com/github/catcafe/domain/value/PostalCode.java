package mabubu0203.com.github.catcafe.domain.value;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public class PostalCode {

  private final String value;

  public PostalCode(String value) {
    // validationを追加する
    this.value = value;
  }

}
