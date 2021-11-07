package mabubu0203.com.github.catcafe.api.controller.store.helper.request;

import java.util.ArrayList;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreSearchServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.FindRequestMapper;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class StoreFindRequestMapper implements FindRequestMapper<StoreSearchServiceInput> {

  private final String cats;
  private final Integer storeId;

  @Override
  public Mono<StoreSearchServiceInput> get() {

    var storeIds = new ArrayList<Integer>();
    storeIds.add(this.storeId);

    return Mono.just(
        StoreSearchServiceInput.builder()
            .cats(this.cats)
            .optStoreIds(Optional.of(storeIds))
            .optPage(Optional.empty())
            .optSize(Optional.empty())
            .optSortKeys(Optional.empty())
            .build());
  }

}
