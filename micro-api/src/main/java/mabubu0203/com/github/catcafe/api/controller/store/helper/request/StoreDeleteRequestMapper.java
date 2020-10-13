package mabubu0203.com.github.catcafe.api.controller.store.helper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreDeleteServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.DeleteRequestMapper;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class StoreDeleteRequestMapper implements DeleteRequestMapper<StoreDeleteServiceInput> {

    private final String cats;
    private final Integer storeId;
    private final Integer version;

    @Override
    public Mono<StoreDeleteServiceInput> get() {
        return Mono.just(
                StoreDeleteServiceInput.builder()
                        .cats(this.cats)
                        .storeId(this.storeId)
                        .version(this.version)
                        .build());
    }

}
