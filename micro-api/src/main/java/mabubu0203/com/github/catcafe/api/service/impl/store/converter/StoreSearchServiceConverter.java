package mabubu0203.com.github.catcafe.api.service.impl.store.converter;

import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreSearchServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreEntity;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreSearchConditions;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StoreSearchServiceConverter {

    public StoreSearchConditions toSearchCondition(StoreSearchServiceInput input) {
        return new StoreSearchConditions(
                input.getOptPage().orElse(0),
                input.getOptSize().orElse(20),
                input.getOptSortKeys()
        )
                .optStoreIds(input.getOptStoreIds());
    }

    public StoreSearchServiceOutput toServiceOutput(Stream<StoreEntity> stream) {
        return new StoreSearchServiceOutput()
                .setStores(stream
                        .map(storeEntity -> new StoreSearchServiceOutput.StoreObject()
                                .setId(storeEntity.getStoreId().get().intValue())
                                .setName(storeEntity.getName())
                        )
                        .collect(Collectors.toList()));
    }

}
