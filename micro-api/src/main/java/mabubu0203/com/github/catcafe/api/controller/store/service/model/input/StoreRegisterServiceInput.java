package mabubu0203.com.github.catcafe.api.controller.store.service.model.input;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;

@Builder
@Getter
public class StoreRegisterServiceInput implements ServiceInput {

  private final String cats;
  private final String name;
  private final LocalDate openDate;
  private final LocalDate closeDate;
  private final String memo;

}
