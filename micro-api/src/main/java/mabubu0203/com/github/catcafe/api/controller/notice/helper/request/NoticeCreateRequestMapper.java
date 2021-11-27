package mabubu0203.com.github.catcafe.api.controller.notice.helper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.input.NoticeResisterServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.CreateRequestMapper;
import org.openapitools.model.NoticeCreateRequest;

@RequiredArgsConstructor
public class NoticeCreateRequestMapper implements
    CreateRequestMapper<NoticeCreateRequest, NoticeResisterServiceInput> {

  private final String cats;

  @Override
  public NoticeResisterServiceInput apply(NoticeCreateRequest noticeCreate) {
    return NoticeResisterServiceInput.builder()
        .cats(this.cats)
        .storeId(noticeCreate.getStoreId())
        .summary(noticeCreate.getSummary())
        .detail(noticeCreate.getDetail())
        .build();
  }

}
