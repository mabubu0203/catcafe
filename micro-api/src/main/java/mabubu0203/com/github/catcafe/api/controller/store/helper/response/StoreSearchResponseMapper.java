package mabubu0203.com.github.catcafe.api.controller.store.helper.response;

import java.time.ZoneOffset;
import java.util.Optional;
import java.util.stream.Collectors;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.SearchResponseMapper;
import org.openapitools.model.Address;
import org.openapitools.model.Common;
import org.openapitools.model.Contact;
import org.openapitools.model.Hours;
import org.openapitools.model.StoreDetail;
import org.openapitools.model.StoreSearchResponse;

public class StoreSearchResponseMapper implements
    SearchResponseMapper<StoreSearchServiceOutput, StoreSearchResponse> {

  @Override
  public StoreSearchResponse apply(StoreSearchServiceOutput storeSearchServiceOutput) {
    var stores = storeSearchServiceOutput.getStores().stream()
        .map(this::convert)
        .collect(Collectors.toList());

    var result = new StoreSearchResponse();
    result.setStores(stores);
    return result;
  }

  private StoreDetail convert(StoreSearchServiceOutput.StoreObject storeObject) {
    var detail = new StoreDetail();
    detail.setId(storeObject.getId());
    detail.setName(storeObject.getName());

    detail.setContact(new Contact());
    detail.setAddress(new Address());
    detail.setHours(new Hours());

    var common = new Common();
    var commonObject = storeObject.getCommon();
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
