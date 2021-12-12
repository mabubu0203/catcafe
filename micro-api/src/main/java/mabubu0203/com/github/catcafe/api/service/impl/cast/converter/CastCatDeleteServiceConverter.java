package mabubu0203.com.github.catcafe.api.service.impl.cast.converter;

import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastCatDeleteServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastCatDeleteServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastCatEntity;
import mabubu0203.com.github.catcafe.domain.value.CastCatId;

public class CastCatDeleteServiceConverter {

  public CastCatEntity fromInput(CastCatDeleteServiceInput input) {
    var castCatId = new CastCatId(input.getCastCatId());
    return CastCatEntity.builder()
        .castCatId(castCatId)
        .version(input.getVersion())
        .build();
  }

  public CastCatDeleteServiceOutput toOutput(CastCatId castCatId) {
    return CastCatDeleteServiceOutput.builder()
        .id(castCatId.value())
        .build();
  }

}
