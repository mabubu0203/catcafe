package mabubu0203.com.github.catcafe.api.controller.notice.service.model.output;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.common.service.model.ServiceOutput;

@Builder
@Getter
public class NoticeSearchServiceOutput implements ServiceOutput {

  private final List<NoticeObject> notices;

  @Builder
  @Getter
  public static class NoticeObject {

    private final Integer id;
    private final Integer storeId;
    private final String summary;
    private final String detail;
    private final CommonObject common;
  }

  @Builder
  @Getter
  public static class CommonObject {

    private final LocalDateTime createdDateTime;
    private final Integer version;
    private final LocalDateTime updatedDateTime;
  }

}
