package mabubu0203.com.github.catcafe.api.controller.notice.helper.response;

import mabubu0203.com.github.catcafe.api.controller.notice.service.model.output.NoticeResisterServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.CreateResponseMapper;
import org.openapitools.model.PostObject;

public class NoticeCreateResponseMapper implements CreateResponseMapper<NoticeResisterServiceOutput, PostObject> {

    @Override
    public PostObject apply(NoticeResisterServiceOutput noticeResisterServiceOutput) {
        return new PostObject().id(noticeResisterServiceOutput.getId());
    }

}
