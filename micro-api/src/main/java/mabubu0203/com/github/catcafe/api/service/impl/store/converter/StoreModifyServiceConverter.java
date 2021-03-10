package mabubu0203.com.github.catcafe.api.service.impl.store.converter;

import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreModifyServiceInput;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreModifyServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreEntity;
import mabubu0203.com.github.catcafe.domain.value.StoreId;

public class StoreModifyServiceConverter {

  public StoreEntity fromInput(StoreModifyServiceInput input) {
    return StoreEntity.builder()
        .storeId(new StoreId(input.getStoreId()))
        .name(input.getName())
        .version(input.getVersion())
        .build();
  }

  public StoreModifyServiceOutput toOutput(StoreId storeId) {
    return StoreModifyServiceOutput.builder()
        .id(storeId.intValue())
        .build();
  }

}
