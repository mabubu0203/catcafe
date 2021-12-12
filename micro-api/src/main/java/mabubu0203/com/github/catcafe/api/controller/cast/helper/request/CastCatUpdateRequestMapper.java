package mabubu0203.com.github.catcafe.api.controller.cast.helper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastCatModifyServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.UpdateRequestMapper;
import org.openapitools.model.CastCatUpdateRequest;

@RequiredArgsConstructor
public class CastCatUpdateRequestMapper implements
    UpdateRequestMapper<CastCatUpdateRequest, CastCatModifyServiceInput> {

  private final String cats;

  @Override
  public CastCatModifyServiceInput apply(CastCatUpdateRequest castCatUpdateRequest) {
    return CastCatModifyServiceInput.builder().build();
  }

}
