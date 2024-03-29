package mabubu0203.com.github.catcafe.api.service.impl.notice.converter;

import mabubu0203.com.github.catcafe.api.controller.notice.service.model.input.NoticeSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.output.NoticeSearchServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeEntity;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeSearchConditions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class NoticeSearchServiceConverter {

  public NoticeSearchConditions toSearchCondition(NoticeSearchServiceInput input) {
    return new NoticeSearchConditions(
        input.getOptPage().orElse(0),
        input.getOptSize().orElse(20),
        input.getOptSortKeys()
    )
        .optStoreIds(input.getOptStoreIds())
        .optNoticeIds(input.getOptNoticeIds());
  }

  public Mono<NoticeSearchServiceOutput> toOutput(Flux<NoticeEntity> flux) {
    return flux.map(this::toNoticeObject)
        .collectList()
        .map(NoticeSearchServiceOutput.builder()::notices)
        .map(NoticeSearchServiceOutput.NoticeSearchServiceOutputBuilder::build);
  }

  private NoticeSearchServiceOutput.NoticeObject toNoticeObject(NoticeEntity noticeEntity) {
    return
        NoticeSearchServiceOutput.NoticeObject.builder()
            .id(noticeEntity.getNoticeIdValue())
            .storeId(noticeEntity.getStoreIdValue())
            .summary(noticeEntity.getSummary())
            .detail(noticeEntity.getDetail())
            .common(
                NoticeSearchServiceOutput.CommonObject.builder()
                    .createdDateTime(noticeEntity.getCreatedDateTime())
                    .version(noticeEntity.getVersion())
                    .updatedDateTime(noticeEntity.getUpdatedDateTime())
                    .build())
            .build();
  }

}
