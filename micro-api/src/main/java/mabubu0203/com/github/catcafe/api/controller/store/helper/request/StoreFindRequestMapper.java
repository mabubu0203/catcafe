package mabubu0203.com.github.catcafe.api.controller.store.helper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreSearchServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.FindRequestMapper;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class StoreFindRequestMapper implements FindRequestMapper<StoreSearchServiceInput> {

    @Override
    public Mono<StoreSearchServiceInput> get() {
        return Mono.empty();
    }

}
