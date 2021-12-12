package mabubu0203.com.github.catcafe.api.service.impl.cast.converter;

import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastCatModifyServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastCatModifyServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastCatEntity;
import mabubu0203.com.github.catcafe.domain.value.CastCatId;

public class CastCatModifyServiceConverter {

  public CastCatEntity fromInput(CastCatModifyServiceInput input) {
    return CastCatEntity.builder().build();
  }

  public CastCatModifyServiceOutput toOutput(CastCatId castCatId) {
    return CastCatModifyServiceOutput.builder()
        .id(castCatId.value()).build();
  }

}
