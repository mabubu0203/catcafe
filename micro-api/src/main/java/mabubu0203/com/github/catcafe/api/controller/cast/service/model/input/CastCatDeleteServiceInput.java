package mabubu0203.com.github.catcafe.api.controller.cast.service.model.input;

import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;

@Builder
@Getter
public class CastCatDeleteServiceInput implements ServiceInput {

  private final Integer castCatId;
  private final Integer version;

}
