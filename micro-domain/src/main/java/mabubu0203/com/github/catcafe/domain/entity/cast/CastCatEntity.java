package mabubu0203.com.github.catcafe.domain.entity.cast;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.domain.value.CastCatId;

@Builder
@Getter
public class CastCatEntity {

  private final CastCatId castCatId;
  private final String name;
  private final String image;
  private final String type;
  private final String sex;
  private final String memo;
  private final LocalDateTime createdDateTime;
  private final Integer version;
  private final LocalDateTime updatedDateTime;

}
