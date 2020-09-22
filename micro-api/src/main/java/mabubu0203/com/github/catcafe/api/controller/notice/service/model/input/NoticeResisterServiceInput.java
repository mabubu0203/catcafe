package mabubu0203.com.github.catcafe.api.controller.notice.service.model.input;

import lombok.Data;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;

@Accessors(chain = true)
@Data
public class NoticeResisterServiceInput implements ServiceInput {

    private Integer storeId;
    private String summary;
    private String detail;

}
