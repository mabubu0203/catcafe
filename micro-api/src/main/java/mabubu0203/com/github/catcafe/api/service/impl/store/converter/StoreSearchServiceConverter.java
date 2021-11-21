package mabubu0203.com.github.catcafe.api.service.impl.store.converter;

import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreSearchServiceOutput;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreSearchServiceOutput.StoreObject;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreSearchServiceOutput.StoreSearchServiceOutputBuilder;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreEntity;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreSearchConditions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class StoreSearchServiceConverter {

  public StoreSearchConditions toSearchCondition(StoreSearchServiceInput input) {
    return new StoreSearchConditions(
        input.getOptPage().orElse(0),
        input.getOptSize().orElse(20),
        input.getOptSortKeys()
    )
        .optStoreIds(input.getOptStoreIds());
  }

  public Mono<StoreSearchServiceOutput> toOutput(Flux<StoreEntity> flux) {
    return flux.map(this::toStoreObject).collectList()
        .map(stores -> StoreSearchServiceOutput.builder().stores(stores))
        .map(StoreSearchServiceOutputBuilder::build);
  }

  private StoreObject toStoreObject(StoreEntity storeEntity) {
    return
        StoreSearchServiceOutput.StoreObject.builder()
            .id(storeEntity.getStoreId().value())
            .name(storeEntity.getName())
            .common(StoreSearchServiceOutput.CommonObject.builder()
                .createdDateTime(storeEntity.getCreatedDateTime())
                .version(storeEntity.getVersion())
                .updatedDateTime(storeEntity.getUpdatedDateTime())
                .build())
            .build();
  }

}
