package mabubu0203.com.github.catcafe.api.service.impl.cast.converter;

import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastRegisterServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastRegisterServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastCatEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastEntity;
import mabubu0203.com.github.catcafe.domain.value.CastCatId;
import mabubu0203.com.github.catcafe.domain.value.CastId;
import mabubu0203.com.github.catcafe.domain.value.StoreId;

public class CastResisterServiceConverter {

  public CastEntity fromInput(CastRegisterServiceInput input) {
    var castCatId = new CastCatId(input.getCastCatId());
    var castCatEntity = CastCatEntity.builder()
        .castCatId(castCatId)
        .build();
    var castId = CastId.emptyId();
    var storeId = new StoreId(input.getStoreId());
    return CastEntity.builder()
        .castId(castId)
        .storeId(storeId)
        .memo(input.getMemo())
        .castCatEntity(castCatEntity)
        .build();
  }

  public CastRegisterServiceOutput toOutput(CastId castId) {
    return CastRegisterServiceOutput.builder()
        .id(castId.value())
        .build();
  }

}
