package mabubu0203.com.github.catcafe.api.controller.store.mapper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.store.model.input.StoreSearchServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.FindRequestMapper;

import java.util.Optional;

@RequiredArgsConstructor
public class StoreFindRequestMapper implements FindRequestMapper<StoreSearchServiceInput> {

    @Override
    public Optional<StoreSearchServiceInput> get() {
        return Optional.empty();
    }

}
