package mabubu0203.com.github.catcafe.api.service.impl.cast.converter;

import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastRegisterServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastRegisterServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastCatEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastEntity;
import mabubu0203.com.github.catcafe.domain.value.CastId;
import mabubu0203.com.github.catcafe.domain.value.Memo;
import mabubu0203.com.github.catcafe.domain.value.StoreId;

public class CastResisterServiceConverter {

  public CastEntity fromInput(CastRegisterServiceInput input) {
    var castId = CastId.emptyId();
    var storeId = new StoreId(input.getStoreId());
    var memo = new Memo(input.getMemo());
    var castCatEntity = CastCatEntity.createByCastCatId(input.getCastCatId());
    return CastEntity.builder()
        .castId(castId)
        .storeId(storeId)
        .memo(memo)
        .castCatEntity(castCatEntity)
        .build();
  }

  public CastRegisterServiceOutput toOutput(CastId castId) {
    return CastRegisterServiceOutput.builder()
        .id(castId.value())
        .build();
  }

}
