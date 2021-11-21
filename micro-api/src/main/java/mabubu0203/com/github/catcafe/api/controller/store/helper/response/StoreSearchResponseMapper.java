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

  private StoreDetail convert(StoreSearchServiceOutput.StoreObject store) {
    var detail = new StoreDetail();
    detail.setId(store.getId());
    detail.setName(store.getName());
    detail.setContact(this.contact());
    detail.setAddress(this.address());
    detail.setOpenDate(null);
    detail.setCloseDate(null);
    detail.setHours(new Hours());
    detail.setMemo(null);

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

  // TODO: 修正予定
  private Contact contact() {
    var contact = new Contact();
    contact.setMailAddress(null);
    contact.setMailAddress(null);
    return contact;
  }

  // TODO: 修正予定
  private Address address() {
    var address = new Address();
    address.setPostalCode(null);
    address.setPrefectureCode(null);
    address.setAddress1(null);
    address.setAddress2(null);
    address.setAddress3(null);
    address.setStreetAddress(null);
    address.setBuildingName(null);
    address.setSupplement(null);
    return address;
  }

}
