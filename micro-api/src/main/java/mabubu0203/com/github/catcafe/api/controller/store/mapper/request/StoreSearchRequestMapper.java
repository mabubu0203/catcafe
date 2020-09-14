package mabubu0203.com.github.catcafe.api.controller.store.mapper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.store.model.input.StoreSearchServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.SearchRequestMapper;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class StoreSearchRequestMapper implements SearchRequestMapper<StoreSearchServiceInput> {

    private final String cats;
    private final List<Integer> storeIds;
    private final Integer page;
    private final Integer size;
    private final List<String> sortKeys;

    @Override
    public Optional<StoreSearchServiceInput> get() {
        return Optional.of(
                StoreSearchServiceInput.builder()
                        .cats(this.cats)
                        .optStoreIds(Optional.ofNullable(this.storeIds))
                        .optPage(Optional.ofNullable(this.page))
                        .optSize(Optional.ofNullable(this.size))
                        .optSortKeys(Optional.ofNullable(this.sortKeys))
                        .build());
    }

}
