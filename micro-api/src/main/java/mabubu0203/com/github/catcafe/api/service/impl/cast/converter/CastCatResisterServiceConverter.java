package mabubu0203.com.github.catcafe.api.service.impl.cast.converter;

import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastCatResisterServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastCatResisterServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastCatEntity;
import mabubu0203.com.github.catcafe.domain.value.CastCatId;
import mabubu0203.com.github.catcafe.domain.value.Memo;

public class CastCatResisterServiceConverter {

  public CastCatEntity fromInput(CastCatResisterServiceInput input) {
    var castCatId = CastCatId.emptyId();
    var memo = new Memo(input.getMemo());
    return CastCatEntity.builder()
        .castCatId(castCatId)
        .name(input.getName())
        .image(input.getImage())
        .memo(memo)
        .build();
  }

  public CastCatResisterServiceOutput toOutput(CastCatId castCatId) {
    return CastCatResisterServiceOutput.builder()
        .id(castCatId.value())
        .build();
  }

}
