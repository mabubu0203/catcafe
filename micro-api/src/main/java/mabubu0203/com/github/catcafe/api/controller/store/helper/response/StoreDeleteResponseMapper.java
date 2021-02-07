package mabubu0203.com.github.catcafe.api.controller.store.helper.response;

import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreDeleteServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.DeleteResponseMapper;

public class StoreDeleteResponseMapper implements
    DeleteResponseMapper<StoreDeleteServiceOutput, Boolean> {

  @Override
  public Boolean apply(StoreDeleteServiceOutput storeDeleteServiceOutput) {
    return true;
  }

}
