package mabubu0203.com.github.catcafe.api.controller.notice.helper.request;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.input.NoticeSearchServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.SearchRequestMapper;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class NoticeSearchRequestMapper implements SearchRequestMapper<NoticeSearchServiceInput> {

  private final String cats;
  private final List<Integer> storeIds;
  private final List<Integer> noticeIds;
  private final Integer page;
  private final Integer size;
  private final List<String> sortKeys;

  @Override
  public Mono<NoticeSearchServiceInput> get() {
    return Mono.just(
        NoticeSearchServiceInput.builder()
            .cats(this.cats)
            .optStoreIds(Optional.ofNullable(this.storeIds))
            .optNoticeIds(Optional.ofNullable(this.noticeIds))
            .optPage(Optional.ofNullable(this.page))
            .optSize(Optional.ofNullable(this.size))
            .optSortKeys(Optional.ofNullable(this.sortKeys))
            .build());
  }

}