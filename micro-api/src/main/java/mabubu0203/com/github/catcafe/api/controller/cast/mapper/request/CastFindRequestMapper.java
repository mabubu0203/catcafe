package mabubu0203.com.github.catcafe.api.controller.cast.mapper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.cast.model.input.CastSearchServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.FindRequestMapper;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
public class CastFindRequestMapper implements FindRequestMapper<CastSearchServiceInput> {

    private final String cats;
    private final Integer storeId;
    private final Integer castId;

    private static final Integer FIND_ONE = 1;

    @Override
    public Optional<CastSearchServiceInput> get() {
        var storeIds = new ArrayList<Integer>();
        storeIds.add(this.storeId);
        var castIds = new ArrayList<Integer>();
        castIds.add(castId);
        return Optional.of(
                CastSearchServiceInput.builder()
                        .cats(this.cats)
                        .storeIds(Optional.of(storeIds))
                        .castIds(Optional.of(castIds))
                        .size(Optional.of(FIND_ONE))
                        .build());
    }

}
