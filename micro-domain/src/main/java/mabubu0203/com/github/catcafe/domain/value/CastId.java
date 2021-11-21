package mabubu0203.com.github.catcafe.domain.value;

import java.util.Optional;

public record CastId(Integer value) {

  public static CastId emptyId() {
    return new CastId(null);
  }

  public boolean isEmpty() {
    return Optional.of(value).isEmpty();
  }

}
