package mabubu0203.com.github.catcafe.api.service.impl.notice.converter;

import mabubu0203.com.github.catcafe.api.controller.notice.service.model.input.NoticeSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.output.NoticeSearchServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeEntity;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeSearchConditions;

import java.util.stream.Stream;

public class NoticeSearchServiceConverter {

    public NoticeSearchConditions toSearchCondition(NoticeSearchServiceInput noticeSearchServiceInput) {
        return new NoticeSearchConditions(
                noticeSearchServiceInput.getOptPage().orElse(0),
                noticeSearchServiceInput.getOptSize().orElse(20),
                noticeSearchServiceInput.getOptSortKeys()
        )
                .optStoreIds(noticeSearchServiceInput.getOptStoreIds());
    }

    public NoticeSearchServiceOutput toServiceOutput(Stream<NoticeEntity> stream) {
        return new NoticeSearchServiceOutput();
    }

}
