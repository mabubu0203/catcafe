package mabubu0203.com.github.catcafe.api.controller.provide.service.mapper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.provide.service.model.input.ProvideServiceSearchServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.SearchRequestMapper;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProvideServiceSearchRequestMapper implements SearchRequestMapper<ProvideServiceSearchServiceInput> {

    private final String cats;
    private final List<Integer> storeIds;

    @Override
    public Optional<ProvideServiceSearchServiceInput> get() {
        return Optional.of(new ProvideServiceSearchServiceInput());
    }

}
