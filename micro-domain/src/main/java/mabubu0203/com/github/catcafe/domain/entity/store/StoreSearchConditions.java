package mabubu0203.com.github.catcafe.domain.entity.store;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.entity.SearchConditions;

import java.util.List;
import java.util.Optional;

@Accessors(fluent = true)
@Getter
@Setter
public class StoreSearchConditions extends SearchConditions {

    private Optional<List<Integer>> optStoreIds;

    public StoreSearchConditions(){
        super();
    }

    public StoreSearchConditions(Integer page, Integer size, Optional<List<String>> optSortKeys) {
        super(page, size, optSortKeys);
    }

}
