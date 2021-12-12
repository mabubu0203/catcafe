package mabubu0203.com.github.catcafe.domain.entity.cast;

import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.entity.SearchConditions;

@Accessors(fluent = true)
@Getter
@Setter
public class CastCatSearchConditions extends SearchConditions {

  private Optional<List<Integer>> optCastCatIds;

  public CastCatSearchConditions() {
    super();
  }

  public CastCatSearchConditions(Integer page, Integer size, Optional<List<String>> optSortKeys) {
    super(page, size, optSortKeys);
  }

}
