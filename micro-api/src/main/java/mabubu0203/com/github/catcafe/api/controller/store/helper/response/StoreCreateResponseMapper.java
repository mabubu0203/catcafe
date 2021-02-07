package mabubu0203.com.github.catcafe.api.controller.store.helper.response;

import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreRegisterServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.CreateResponseMapper;
import org.openapitools.model.PostObject;

public class StoreCreateResponseMapper implements
    CreateResponseMapper<StoreRegisterServiceOutput, PostObject> {

  @Override
  public PostObject apply(StoreRegisterServiceOutput storeRegisterServiceOutput) {
    return new PostObject().id(storeRegisterServiceOutput.getId());
  }

}
