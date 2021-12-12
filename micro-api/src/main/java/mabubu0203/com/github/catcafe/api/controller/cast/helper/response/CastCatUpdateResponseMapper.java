package mabubu0203.com.github.catcafe.api.controller.cast.helper.response;

import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastCatModifyServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.UpdateResponseMapper;
import org.openapitools.model.PatchObject;

public class CastCatUpdateResponseMapper implements
    UpdateResponseMapper<CastCatModifyServiceOutput, PatchObject> {

  @Override
  public PatchObject apply(CastCatModifyServiceOutput castCatModifyServiceOutput) {
    return new PatchObject().id(castCatModifyServiceOutput.getId());
  }
}
