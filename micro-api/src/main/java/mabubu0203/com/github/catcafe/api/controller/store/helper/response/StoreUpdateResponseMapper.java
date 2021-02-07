package mabubu0203.com.github.catcafe.api.controller.store.helper.response;

import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreModifyServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.UpdateResponseMapper;
import org.openapitools.model.PatchObject;

public class StoreUpdateResponseMapper implements
    UpdateResponseMapper<StoreModifyServiceOutput, PatchObject> {

  @Override
  public PatchObject apply(StoreModifyServiceOutput storeModifyServiceOutput) {
    return new PatchObject().id(storeModifyServiceOutput.getId());
  }

}
