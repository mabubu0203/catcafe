package mabubu0203.com.github.catcafe.api.controller.cast.mapper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.cast.model.input.CastSearchServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.SearchRequestMapper;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CastSearchRequestMapper implements SearchRequestMapper<CastSearchServiceInput> {

    private final String cats;
    private final List<Integer> storeIds;
    private final List<Integer> castIds;
    private final Integer page;
    private final Integer size;
    private final List<String> sortKeys;

    @Override
    public Optional<CastSearchServiceInput> get() {
        return Optional.of(
                CastSearchServiceInput.builder()
                        .cats(this.cats)
                        .optStoreIds(Optional.ofNullable(this.storeIds))
                        .optCastIds(Optional.ofNullable(this.castIds))
                        .optPage(Optional.ofNullable(this.page))
                        .optSize(Optional.ofNullable(this.size))
                        .optSortKeys(Optional.ofNullable(this.sortKeys))
                        .build());
    }

}
