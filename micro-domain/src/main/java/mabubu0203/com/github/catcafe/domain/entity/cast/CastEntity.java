package mabubu0203.com.github.catcafe.domain.entity.cast;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.domain.value.CastId;
import mabubu0203.com.github.catcafe.domain.value.StoreId;

@Builder
@Getter
public class CastEntity {

  private final CastId castId;
  private final StoreId storeId;
  private final String memo;
  private final LocalDateTime createdDateTime;
  private final Integer version;
  private final LocalDateTime updatedDateTime;
  private final CastCatEntity CastCatEntity;

}
