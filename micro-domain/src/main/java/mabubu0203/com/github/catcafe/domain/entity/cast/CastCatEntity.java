package mabubu0203.com.github.catcafe.domain.entity.cast;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.domain.value.CastCatId;
import mabubu0203.com.github.catcafe.domain.value.CastId;
import mabubu0203.com.github.catcafe.domain.value.Memo;

/**
 * キャスト(猫)
 */
@Builder
@Getter
public class CastCatEntity {

  private final CastCatId castCatId;
  private final String name;
  private final String image;
  private final String type;
  private final String sex;
  private final Memo memo;
  private final LocalDateTime createdDateTime;
  private final Integer version;
  private final LocalDateTime updatedDateTime;

  public static CastCatEntity createByCastCatId(Integer castCatId){
    return CastCatEntity.builder()
        .castCatId(new CastCatId(castCatId))
        .build();
  }

  public Integer getCastCatIdValue() {
    return Optional.of(this.castCatId)
        .map(CastCatId::value)
        .orElse(null);
  }

  public String getMemoValue() {
    return Optional.of(this.memo)
        .map(Memo::value)
        .orElse(null);
  }

}
