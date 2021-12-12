package mabubu0203.com.github.catcafe.domain.entity.cast;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.domain.value.CastCatId;
import mabubu0203.com.github.catcafe.domain.value.HttpUrl;
import mabubu0203.com.github.catcafe.domain.value.Memo;
import mabubu0203.com.github.catcafe.domain.value.cast.CatSex;

/**
 * キャスト(猫)
 */
@Builder
@Getter
public class CastCatEntity {

  private final CastCatId castCatId;
  private final String name;
  private final HttpUrl image;
  private final String type;
  private final CatSex sex;
  private final LocalDate birthdayDate;
  private final List<CastCatId> brothers;
  private final List<CastCatId> sisters;
  private final Memo memo;
  private final LocalDateTime createdDateTime;
  private final Integer version;
  private final LocalDateTime updatedDateTime;

  public static CastCatEntity createByCastCatId(Integer castCatId) {
    return CastCatEntity.builder()
        .castCatId(new CastCatId(castCatId))
        .build();
  }

  public Integer getCastCatIdValue() {
    return Optional.ofNullable(this.castCatId)
        .map(CastCatId::value)
        .orElse(null);
  }

  public String getImageValue() {
    return Optional.ofNullable(this.image)
        .map(HttpUrl::value)
        .orElse(null);
  }

  public Integer getSexCode() {
    return Optional.ofNullable(this.sex)
        .map(CatSex::getCode)
        .orElse(null);
  }

  public String getSexLabel() {
    return Optional.ofNullable(this.sex)
        .map(CatSex::getLabel)
        .orElse(null);
  }

  public String getMemoValue() {
    return Optional.ofNullable(this.memo)
        .map(Memo::value)
        .orElse(null);
  }

}
