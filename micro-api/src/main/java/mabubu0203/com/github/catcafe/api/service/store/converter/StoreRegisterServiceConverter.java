package mabubu0203.com.github.catcafe.api.service.store.converter;

import mabubu0203.com.github.catcafe.api.service.store.model.input.StoreRegisterServiceInput;
import mabubu0203.com.github.catcafe.api.service.store.model.output.StoreRegisterServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreEntity;
import mabubu0203.com.github.catcafe.domain.value.StoreId;

public class StoreRegisterServiceConverter {

    public StoreEntity fromInput(StoreRegisterServiceInput input) {
        return StoreEntity.builder()
                .name(input.getName())
                .build();
    }

    public StoreRegisterServiceOutput toOutput(StoreId storeId) {
        return new StoreRegisterServiceOutput().setId(storeId.intValue());
    }

}
