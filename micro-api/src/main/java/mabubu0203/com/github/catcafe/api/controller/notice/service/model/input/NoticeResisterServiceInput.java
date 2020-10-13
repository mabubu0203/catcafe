package mabubu0203.com.github.catcafe.api.controller.notice.service.model.input;

import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;

@Builder
@Getter
public class NoticeResisterServiceInput implements ServiceInput {

    private final String cats;
    private final Integer storeId;
    private final String summary;
    private final String detail;

}
