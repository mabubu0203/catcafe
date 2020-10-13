package mabubu0203.com.github.catcafe.api.service.impl.notice.converter;

import mabubu0203.com.github.catcafe.api.controller.notice.service.model.input.NoticeResisterServiceInput;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.output.NoticeResisterServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeEntity;
import mabubu0203.com.github.catcafe.domain.value.NoticeId;
import mabubu0203.com.github.catcafe.domain.value.StoreId;

public class NoticeResisterServiceConverter {

    public NoticeEntity fromInput(NoticeResisterServiceInput input) {
        return NoticeEntity.builder()
                .storeId(new StoreId(input.getStoreId()))
                .detail(input.getDetail())
                .summary(input.getSummary())
                .build();
    }

    public NoticeResisterServiceOutput toOutput(NoticeId noticeId) {
        return NoticeResisterServiceOutput.builder()
                .id(noticeId.intValue())
                .build();
    }

}
