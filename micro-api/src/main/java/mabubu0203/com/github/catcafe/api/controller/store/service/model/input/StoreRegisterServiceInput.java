package mabubu0203.com.github.catcafe.api.controller.store.service.model.input;

import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;
import java.time.LocalDate;

@Builder
@Getter
public class StoreRegisterServiceInput implements ServiceInput {

  private final String cats;
  private final String name;
  private final LocalDate openDate;
  private final LocalDate closeDate;
  private final String memo;

}
