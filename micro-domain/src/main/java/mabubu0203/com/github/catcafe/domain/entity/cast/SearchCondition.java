package mabubu0203.com.github.catcafe.domain.entity.cast;

import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class SearchCondition {

    private Optional<List<Integer>> optStoreIds;
    private Optional<List<Integer>> optCastIds;
    private Integer page;
    private Integer size;
    private Optional<String> optSortKey;

}
