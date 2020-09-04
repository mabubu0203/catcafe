package mabubu0203.com.github.catcafe.api.controller.cast.mapper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.cast.model.input.CastSearchServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.FindRequestMapper;

import java.util.Optional;

@RequiredArgsConstructor
public class CastFindRequestMapper implements FindRequestMapper<CastSearchServiceInput> {

    private final String cats;
    private final Integer storeId;
    private final Integer castId;

    @Override
    public Optional<CastSearchServiceInput> get() {
        return Optional.of(new CastSearchServiceInput());
    }

}
