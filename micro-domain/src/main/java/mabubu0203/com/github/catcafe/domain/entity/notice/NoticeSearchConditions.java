package mabubu0203.com.github.catcafe.domain.entity.notice;

import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.entity.SearchConditions;

@Accessors(fluent = true)
@Getter
@Setter
public class NoticeSearchConditions extends SearchConditions {

  private Optional<List<Integer>> optStoreIds;
  private Optional<List<Integer>> optNoticeIds;

  public NoticeSearchConditions() {
    super();
  }

  public NoticeSearchConditions(Integer page, Integer size, Optional<List<String>> optSortKeys) {
    super(page, size, optSortKeys);
  }

}
