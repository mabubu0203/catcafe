package mabubu0203.com.github.catcafe.api.controller.notice.helper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.input.NoticeResisterServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.CreateRequestMapper;
import org.openapitools.model.NoticeCreate;

@RequiredArgsConstructor
public class NoticeCreateRequestMapper implements CreateRequestMapper<NoticeCreate, NoticeResisterServiceInput> {

    private final String cats;

    @Override
    public NoticeResisterServiceInput apply(NoticeCreate noticeCreate) {
        return new NoticeResisterServiceInput()
                .setStoreId(noticeCreate.getStoreId())
                .setSummary(noticeCreate.getSummary())
                .setDetail(noticeCreate.getDetail());
    }

}
