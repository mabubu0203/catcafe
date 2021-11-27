package mabubu0203.com.github.catcafe.api.controller.notice.helper.response;

import java.time.ZoneOffset;
import java.util.Optional;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.output.NoticeSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.SearchResponseMapper;
import org.openapitools.model.Common;
import org.openapitools.model.NoticeDetail;
import org.openapitools.model.NoticeSearchResponse;

public class NoticeSearchResponseMapper implements
    SearchResponseMapper<NoticeSearchServiceOutput, NoticeSearchResponse> {

  @Override
  public NoticeSearchResponse apply(NoticeSearchServiceOutput noticeSearchServiceOutput) {
    var notices = noticeSearchServiceOutput.getNotices().stream()
        .map(this::convert)
        .toList();

    var result = new NoticeSearchResponse();
    result.setNotices(notices);
    return result;
  }

  private NoticeDetail convert(NoticeSearchServiceOutput.NoticeObject notice) {
    var detail = new NoticeDetail();
    var common = this.common(notice.getCommon());
    detail.setId(notice.getId());
    detail.setStoreId(notice.getStoreId());
    detail.setSummary(notice.getSummary());
    detail.setDetail(notice.getDetail());
    detail.setPublicationStartDateTime(null);
    detail.setPublicationEndDateTime(null);
    detail.setCommon(common);
    return detail;
  }

  private Common common(NoticeSearchServiceOutput.CommonObject object) {
    var common = new Common();
    common.setCreatedDateTime(object.getCreatedDateTime().atOffset(ZoneOffset.ofHours(9)));
    common.setVersion(object.getVersion());
    Optional.ofNullable(object.getUpdatedDateTime())
        .map(ldt -> ldt.atOffset(ZoneOffset.ofHours(9)))
        .ifPresent(common::setUpdatedDateTime);
    return common;
  }

}