package mabubu0203.com.github.catcafe.api.controller.store.mapper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.store.model.input.StoreRegisterServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.CreateRequestMapper;
import org.openapitools.model.StoreCreate;

import java.util.Optional;

@RequiredArgsConstructor
public class StoreCreateRequestMapper implements CreateRequestMapper<StoreCreate, StoreRegisterServiceInput> {

    private final String cats;

    @Override
    public StoreRegisterServiceInput apply(StoreCreate storeCreate) {
        return new StoreRegisterServiceInput().setName(storeCreate.getName());
    }

}
