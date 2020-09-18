package mabubu0203.com.github.catcafe.api.controller.provide.service.helper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.provide.service.service.model.input.ProvideServiceSearchServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.SearchRequestMapper;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class ProvideServiceSearchRequestMapper implements SearchRequestMapper<ProvideServiceSearchServiceInput> {

    private final String cats;
    private final List<Integer> storeIds;

    @Override
    public Mono<ProvideServiceSearchServiceInput> get() {
        return Mono.just(new ProvideServiceSearchServiceInput());
    }

}
