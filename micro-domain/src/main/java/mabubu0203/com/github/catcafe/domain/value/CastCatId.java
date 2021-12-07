package mabubu0203.com.github.catcafe.domain.value;

import java.util.Optional;

/**
 * キャスト(猫)ID
 */
public record CastCatId(Integer value) {

  public static CastCatId emptyId() {
    return new CastCatId(null);
  }

  public boolean isEmpty() {
    return Optional.ofNullable(value).isEmpty();
  }

}
