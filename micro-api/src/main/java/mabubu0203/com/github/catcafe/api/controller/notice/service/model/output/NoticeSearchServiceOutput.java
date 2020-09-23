package mabubu0203.com.github.catcafe.api.controller.notice.service.model.output;

import lombok.Data;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.service.model.ServiceOutput;

import java.util.List;

@Accessors(chain = true)
@Data
public class NoticeSearchServiceOutput implements ServiceOutput {

    private List<NoticeObject> notices;

    @Data
    public static class NoticeObject {
        private Integer id;
        private Integer storeId;
        private String summary;
        private String detail;
    }

}
