package mabubu0203.com.github.catcafe.api.controller.store.helper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreRegisterServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.CreateRequestMapper;
import org.openapitools.model.StoreCreate;

@RequiredArgsConstructor
public class StoreCreateRequestMapper implements
    CreateRequestMapper<StoreCreate, StoreRegisterServiceInput> {

  private final String cats;

  @Override
  public StoreRegisterServiceInput apply(StoreCreate storeCreate) {
    return StoreRegisterServiceInput.builder()
        .cats(this.cats)
        .name(storeCreate.getName())
        .openDate(storeCreate.getOpenDate())
        .closeDate(storeCreate.getCloseDate())
        .memo(storeCreate.getMemo())
        .build();
  }

}
