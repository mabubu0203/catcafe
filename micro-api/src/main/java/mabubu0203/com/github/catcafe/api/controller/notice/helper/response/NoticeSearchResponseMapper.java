package mabubu0203.com.github.catcafe.api.controller.notice.helper.response;

import mabubu0203.com.github.catcafe.api.controller.notice.service.model.output.NoticeSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.SearchResponseMapper;
import org.openapitools.model.Common;
import org.openapitools.model.NoticeDetail;
import org.openapitools.model.NoticeSearchResponse;

import java.time.ZoneOffset;
import java.util.Optional;
import java.util.stream.Collectors;

public class NoticeSearchResponseMapper implements SearchResponseMapper<NoticeSearchServiceOutput, NoticeSearchResponse> {

    @Override
    public NoticeSearchResponse apply(NoticeSearchServiceOutput noticeSearchServiceOutput) {
        var notices = noticeSearchServiceOutput.getNotices().stream()
                .map(this::convert)
                .collect(Collectors.toList());

        var result = new NoticeSearchResponse();
        result.setNotices(notices);
        return result;
    }

    private NoticeDetail convert(NoticeSearchServiceOutput.NoticeObject notice) {
        var detail = new NoticeDetail();
        detail.setId(notice.getId());
        detail.setStoreId(notice.getStoreId());
        detail.setSummary(notice.getSummary());
        detail.setDetail(notice.getDetail());
        detail.setPublicationStartDateTime(null);
        detail.setPublicationEndDateTime(null);

        var common = new Common();
        var commonObject = notice.getCommon();
        common.setCreatedDateTime(commonObject.getCreatedDateTime().atOffset(ZoneOffset.ofHours(9)));
        common.setVersion(commonObject.getVersion());
        common.setUpdatedDateTime(
                Optional.ofNullable(commonObject.getUpdatedDateTime())
                        .map(ldt -> ldt.atOffset(ZoneOffset.ofHours(9)))
                        .orElse(null));
        detail.setCommon(common);
        return detail;
    }

}