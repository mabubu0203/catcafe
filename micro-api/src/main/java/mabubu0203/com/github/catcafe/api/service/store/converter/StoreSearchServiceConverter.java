package mabubu0203.com.github.catcafe.api.service.store.converter;

import mabubu0203.com.github.catcafe.api.service.store.model.input.StoreSearchServiceInput;
import mabubu0203.com.github.catcafe.api.service.store.model.output.StoreSearchServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreEntity;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreSearchConditions;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StoreSearchServiceConverter {

    public StoreSearchConditions toSearchCondition(StoreSearchServiceInput storeSearchServiceInput) {
        return new StoreSearchConditions(
                storeSearchServiceInput.getOptPage().orElse(0),
                storeSearchServiceInput.getOptSize().orElse(20),
                storeSearchServiceInput.getOptSortKeys()
        )
                .optStoreIds(storeSearchServiceInput.getOptStoreIds());
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
