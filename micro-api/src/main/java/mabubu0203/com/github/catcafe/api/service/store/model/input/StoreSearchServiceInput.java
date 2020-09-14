package mabubu0203.com.github.catcafe.api.service.store.model.input;

import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;

import java.util.List;
import java.util.Optional;

@Builder
@Getter
public class StoreSearchServiceInput implements ServiceInput {

    private final String cats;
    private final Optional<List<Integer>> optStoreIds;
    private final Optional<Integer> optPage;
    private final Optional<Integer> optSize;
    private final Optional<List<String>> optSortKeys;

}
