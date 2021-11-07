package mabubu0203.com.github.catcafe.api.controller.store.helper.response;

import java.time.ZoneOffset;
import java.util.Optional;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.FindResponseMapper;
import org.openapitools.model.Address;
import org.openapitools.model.Common;
import org.openapitools.model.Contact;
import org.openapitools.model.Hours;
import org.openapitools.model.StoreDetail;
import org.openapitools.model.StoreFindResponse;

public class StoreFindResponseMapper implements
    FindResponseMapper<StoreSearchServiceOutput, StoreFindResponse> {

  @Override
  public StoreFindResponse apply(StoreSearchServiceOutput storeSearchServiceOutput) {
    var result = new StoreFindResponse();
    storeSearchServiceOutput.getStores().stream()
        .findFirst()
        .ifPresent(store -> result.setStore(this.convert(store)));
    return result;
  }

  private StoreDetail convert(StoreSearchServiceOutput.StoreObject store) {
    var detail = new StoreDetail();
    detail.setId(store.getId());
    detail.setName(store.getName());
    detail.setContact(new Contact());
    detail.setAddress(new Address());
    detail.setOpenDate(null);
    detail.setCloseDate(null);
    detail.setHours(new Hours());
    detail.setMemo("");

    var common = new Common();
    var commonObject = store.getCommon();
    common.setCreatedDateTime(commonObject.getCreatedDateTime().atOffset(ZoneOffset.ofHours(9)));
    common.setVersion(commonObject.getVersion());
    common.setUpdatedDateTime(
        Optional.ofNullable(commonObject.getUpdatedDateTime())
            .map(ldt -> ldt.atOffset(ZoneOffset.ofHours(9)))
            .orElse(null));
    detail.setCommon(common);
    return detail;
  }

}
