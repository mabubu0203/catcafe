package mabubu0203.com.github.catcafe.domain.entity.cast;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.domain.value.CastCatId;
import mabubu0203.com.github.catcafe.domain.value.CastId;
import mabubu0203.com.github.catcafe.domain.value.Memo;
import mabubu0203.com.github.catcafe.domain.value.StoreId;

/**
 * キャスト
 */
@Builder
@Getter
public class CastEntity {

  private final CastId castId;
  private final StoreId storeId;
  private final Memo memo;
  private final LocalDateTime createdDateTime;
  private final Integer version;
  private final LocalDateTime updatedDateTime;
  private final CastCatEntity castCatEntity;

  public Integer getCastIdValue() {
    return Optional.of(this.castId)
        .map(CastId::value)
        .orElse(null);
  }

  public Integer getStoreIdValue() {
    return Optional.of(this.storeId)
        .map(StoreId::value)
        .orElse(null);
  }

  public Integer getCastCatIdValue() {
    return Optional.of(this.castCatEntity)
        .map(CastCatEntity::getCastCatId)
        .map(CastCatId::value)
        .orElse(null);
  }

  public String getMemoValue() {
    return Optional.of(this.memo)
        .map(Memo::value)
        .orElse(null);
  }

}
