package mabubu0203.com.github.catcafe.api.service.impl.store.converter;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreSearchServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreEntity;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreSearchConditions;

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
    return StoreSearchServiceOutput.builder()
        .stores(stream
            .map(storeEntity ->
                StoreSearchServiceOutput.StoreObject.builder()
                    .id(storeEntity.getStoreId().intValue())
                    .name(storeEntity.getName())
                    .common(StoreSearchServiceOutput.CommonObject.builder()
                        .createdDateTime(storeEntity.getCreatedDateTime())
                        .version(storeEntity.getVersion())
                        .updatedDateTime(storeEntity.getUpdatedDateTime())
                        .build())
                    .build()
            )
            .collect(Collectors.toList()))
        .build();
  }

}
