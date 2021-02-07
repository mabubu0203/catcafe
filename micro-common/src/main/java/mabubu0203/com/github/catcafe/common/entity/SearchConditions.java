package mabubu0203.com.github.catcafe.common.entity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
public abstract class SearchConditions {

  private final Integer page;
  private final Integer size;
  private final Optional<List<String>> optSortKeys;

  public SearchConditions() {
    this.page = 0;
    this.size = 1;
    this.optSortKeys = Optional.empty();
  }

  public Pageable getPageRequest() {
    var sort = Sort.unsorted();
    this.optSortKeys.orElse(Collections.emptyList())
        .stream()
        .map(Sort::by)
        .forEach(sort::and);
    return PageRequest.of(this.page, this.size, sort);
  }

}
