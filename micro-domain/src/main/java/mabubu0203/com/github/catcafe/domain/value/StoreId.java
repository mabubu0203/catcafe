package mabubu0203.com.github.catcafe.domain.value;

import java.util.Optional;

public record StoreId(Integer value) {

  public static StoreId emptyId() {
    return new StoreId(null);
  }

  public boolean isEmpty() {
    return Optional.of(value).isEmpty();
  }

}
