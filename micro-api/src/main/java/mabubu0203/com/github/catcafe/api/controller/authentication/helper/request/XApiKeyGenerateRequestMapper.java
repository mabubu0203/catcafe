package mabubu0203.com.github.catcafe.api.controller.authentication.helper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.authentication.service.model.input.XApiKeyGenerateServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.CreateRequestMapper;
import org.openapitools.model.XApiKeyGenerate;

@RequiredArgsConstructor
public class XApiKeyGenerateRequestMapper implements
    CreateRequestMapper<XApiKeyGenerate, XApiKeyGenerateServiceInput> {

  private final String cats;
  private final String clientIp;

  @Override
  public XApiKeyGenerateServiceInput apply(XApiKeyGenerate xApiKeyGenerate) {
    return XApiKeyGenerateServiceInput.builder()
        .cats(this.cats)
        .random(xApiKeyGenerate.getRandom())
        .clientIp(this.clientIp)
        .build();
  }

}
