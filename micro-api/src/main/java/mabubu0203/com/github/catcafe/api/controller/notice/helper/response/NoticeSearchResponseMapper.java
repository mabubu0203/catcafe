package mabubu0203.com.github.catcafe.api.controller.notice.helper.response;

import mabubu0203.com.github.catcafe.api.controller.notice.service.model.output.NoticeSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.SearchResponseMapper;
import org.openapitools.model.NoticeDetail;
import org.openapitools.model.NoticeSearchResponse;

public class NoticeSearchResponseMapper implements SearchResponseMapper<NoticeSearchServiceOutput, NoticeSearchResponse> {

    @Override
    public NoticeSearchResponse apply(NoticeSearchServiceOutput noticeSearchServiceOutput) {
        return this.search();
    }

    private NoticeSearchResponse search() {
        var detail = new NoticeDetail();
        detail.setId(1);
        detail.setSummary("おしらせ1");
        detail.setDetail("おしらせ1の詳細です。");

        var result = new NoticeSearchResponse();
        result.addNoticesItem(detail);
        return result;
    }

}