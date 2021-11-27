package mabubu0203.com.github.catcafe.api.controller.store.helper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreModifyServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.UpdateRequestMapper;
import org.openapitools.model.StoreUpdateRequest;

@RequiredArgsConstructor
public class StoreUpdateRequestMapper implements
    UpdateRequestMapper<StoreUpdateRequest, StoreModifyServiceInput> {

  private final String cats;
  private final Integer storeId;

  @Override
  public StoreModifyServiceInput apply(StoreUpdateRequest storeUpdate) {
    return StoreModifyServiceInput.builder()
        .cats(this.cats)
        .storeId(this.storeId)
        .name(storeUpdate.getName())
        .version(storeUpdate.getVersion())
        .build();
  }

}
