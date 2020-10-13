package mabubu0203.com.github.catcafe.api.service.impl.store.converter;

import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreDeleteServiceInput;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreDeleteServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreEntity;
import mabubu0203.com.github.catcafe.domain.value.StoreId;

public class StoreDeleteServiceConverter {

    public StoreEntity fromInput(StoreDeleteServiceInput input) {
        return StoreEntity.builder()
                .storeId(new StoreId(input.getStoreId()))
                .version(input.getVersion())
                .build();
    }

    public StoreDeleteServiceOutput toOutput(StoreId storeId) {
        return StoreDeleteServiceOutput.builder()
                .id(storeId.intValue())
                .build();
    }

}
