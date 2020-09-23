package mabubu0203.com.github.catcafe.api.controller.notice.helper.response;

import mabubu0203.com.github.catcafe.api.controller.notice.service.model.output.NoticeSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.SearchResponseMapper;
import org.openapitools.model.NoticeDetail;
import org.openapitools.model.NoticeSearchResponse;

public class NoticeSearchResponseMapper implements SearchResponseMapper<NoticeSearchServiceOutput, NoticeSearchResponse> {

    @Override
    public NoticeSearchResponse apply(NoticeSearchServiceOutput noticeSearchServiceOutput) {
        var result = new NoticeSearchResponse();
        for (NoticeSearchServiceOutput.NoticeObject notice : noticeSearchServiceOutput.getNotices()) {
            var detail = new NoticeDetail();
            detail.setId(notice.getId());
            detail.setSummary("おしらせ1");
            detail.setDetail("おしらせ1の詳細です。");
            result.addNoticesItem(detail);
        }
        return result;
    }

}