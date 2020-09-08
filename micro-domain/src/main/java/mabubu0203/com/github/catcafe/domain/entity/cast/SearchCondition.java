package mabubu0203.com.github.catcafe.domain.entity.cast;

import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class SearchCondition {

    private Optional<List<Integer>> storeIds;
    private Optional<List<Integer>> castIds;
    private Optional<Integer> size;

}
