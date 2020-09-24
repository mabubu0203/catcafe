package mabubu0203.com.github.catcafe.api.service.impl.notice.converter;

import mabubu0203.com.github.catcafe.api.controller.notice.service.model.input.NoticeSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.output.NoticeSearchServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeEntity;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeSearchConditions;

import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return new NoticeSearchServiceOutput()
                .setNotices(stream
                        .map(noticeEntity -> new NoticeSearchServiceOutput.NoticeObject()
                                .setId(noticeEntity.getNoticeId().get().intValue())
                                .setStoreId(noticeEntity.getStoreId().intValue())
                                .setSummary(noticeEntity.getSummary())
                                .setDetail(noticeEntity.getDetail())
                        )
                        .collect(Collectors.toList()));
    }

}
