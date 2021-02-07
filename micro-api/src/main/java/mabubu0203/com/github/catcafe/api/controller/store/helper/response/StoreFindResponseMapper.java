package mabubu0203.com.github.catcafe.api.controller.store.helper.response;

import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.FindResponseMapper;
import org.openapitools.model.Address;
import org.openapitools.model.Contact;
import org.openapitools.model.Hours;
import org.openapitools.model.StoreDetail;
import org.openapitools.model.StoreFindResponse;

public class StoreFindResponseMapper implements
    FindResponseMapper<StoreSearchServiceOutput, StoreFindResponse> {

  @Override
  public StoreFindResponse apply(StoreSearchServiceOutput storeSearchServiceOutput) {
    var result = new StoreFindResponse();
    result.setStore(this.convert(storeSearchServiceOutput.getStores().get(0)));
    return result;
  }

  private StoreDetail convert(StoreSearchServiceOutput.StoreObject store) {
    var detail = new StoreDetail();
    detail.setId(store.getId());
    detail.setName(store.getName());

    detail.setContact(new Contact());
    detail.setAddress(new Address());
    detail.setHours(new Hours());
    return detail;
  }

}
