package mabubu0203.com.github.catcafe.api.service.impl.store.converter;

import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreRegisterServiceInput;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreRegisterServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreEntity;
import mabubu0203.com.github.catcafe.domain.value.StoreId;

public class StoreRegisterServiceConverter {

  public StoreEntity fromInput(StoreRegisterServiceInput input) {
    return StoreEntity.builder()
        .name(input.getName())
        .build();
  }

  public StoreRegisterServiceOutput toOutput(StoreId storeId) {
    return StoreRegisterServiceOutput.builder()
        .id(storeId.intValue())
        .build();
  }

}
