package mabubu0203.com.github.catcafe.api.service.impl.notice.converter;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.input.NoticeSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.output.NoticeSearchServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeEntity;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeSearchConditions;

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

  public NoticeSearchServiceOutput toServiceOutput(Stream<NoticeEntity> stream) {
    return NoticeSearchServiceOutput.builder()
        .notices(stream
            .map(noticeEntity ->
                NoticeSearchServiceOutput.NoticeObject.builder()
                    .id(noticeEntity.getNoticeId().intValue())
                    .storeId(noticeEntity.getStoreId().intValue())
                    .summary(noticeEntity.getSummary())
                    .detail(noticeEntity.getDetail())
                    .common(NoticeSearchServiceOutput.CommonObject.builder()
                        .createdDateTime(noticeEntity.getCreatedDateTime())
                        .version(noticeEntity.getVersion())
                        .updatedDateTime(noticeEntity.getUpdatedDateTime())
                        .build())
                    .build()
            )
            .collect(Collectors.toList()))
        .build();
  }

}
