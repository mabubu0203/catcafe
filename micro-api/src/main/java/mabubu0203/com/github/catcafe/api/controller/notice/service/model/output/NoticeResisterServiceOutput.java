package mabubu0203.com.github.catcafe.api.controller.notice.service.model.output;

import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.common.service.model.ServiceOutput;

@Builder
@Getter
public class NoticeResisterServiceOutput implements ServiceOutput {

  private final Integer id;

}
