package mabubu0203.com.github.catcafe.api.controller.cast.helper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastSearchServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.FindRequestMapper;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
public class CastFindRequestMapper implements FindRequestMapper<CastSearchServiceInput> {

    private final String cats;
    private final Integer storeId;
    private final Integer castId;

    @Override
    public Mono<CastSearchServiceInput> get() {
        var storeIds = new ArrayList<Integer>();
        storeIds.add(this.storeId);
        var castIds = new ArrayList<Integer>();
        castIds.add(this.castId);
        return Mono.just(
                CastSearchServiceInput.builder()
                        .cats(this.cats)
                        .optStoreIds(Optional.of(storeIds))
                        .optCastIds(Optional.of(castIds))
                        .optPage(Optional.empty())
                        .optSize(Optional.empty())
                        .optSortKeys(Optional.empty())
                        .build());
    }

}
