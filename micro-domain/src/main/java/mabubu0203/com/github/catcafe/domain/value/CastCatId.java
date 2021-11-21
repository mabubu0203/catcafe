package mabubu0203.com.github.catcafe.domain.value;

import java.util.Optional;

public record CastCatId(Integer value) {

  public static CastCatId emptyId() {
    return new CastCatId(null);
  }

  public boolean isEmpty() {
    return Optional.of(value).isEmpty();
  }

}
