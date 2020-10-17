package mabubu0203.com.github.catcafe.api.controller.store.helper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreModifyServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.UpdateRequestMapper;
import org.openapitools.model.StoreUpdate;

@RequiredArgsConstructor
public class StoreUpdateRequestMapper implements UpdateRequestMapper<StoreUpdate, StoreModifyServiceInput> {

    private final String cats;
    private final Integer storeId;

    @Override
    public StoreModifyServiceInput apply(StoreUpdate storeUpdate) {
        return StoreModifyServiceInput.builder()
                .cats(this.cats)
                .storeId(this.storeId)
                .name(storeUpdate.getName())
                .version(storeUpdate.getVersion())
                .build();
    }

}
