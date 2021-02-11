package mabubu0203.com.github.catcafe.api.controller.authentication.service.model.input;

import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;

@Builder
@Getter
public class XApiKeyGenerateServiceInput implements ServiceInput {

  private final String cats;
  private final String random;
  private final String clientIp;

}
