package mabubu0203.com.github.catcafe.api.controller.notice.service.model.input;

import java.util.List;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;

@Builder
@Getter
public class NoticeSearchServiceInput implements ServiceInput {

  private final String cats;
  private final Optional<List<Integer>> optStoreIds;
  private final Optional<List<Integer>> optNoticeIds;
  private final Optional<Integer> optPage;
  private final Optional<Integer> optSize;
  private final Optional<List<String>> optSortKeys;

}
