package mabubu0203.com.github.catcafe.api.controller.store.helper.response;

import java.time.ZoneOffset;
import java.util.Optional;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreSearchServiceOutput;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreSearchServiceOutput.AddressObject;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreSearchServiceOutput.CommonObject;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreSearchServiceOutput.ContactObject;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreSearchServiceOutput.HoursObject;
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
    detail.setContact(this.contact(store.getContact()));
    detail.setAddress(this.address(store.getAddress()));
    detail.setOpenDate(store.getOpenDate());
    detail.setCloseDate(store.getCloseDate());
    detail.setHours(this.hours(store.getHours()));
    detail.setMemo(store.getMemo());
    detail.setCommon(this.common(store.getCommon()));
    return detail;
  }

  private Contact contact(ContactObject object) {
    var contact = new Contact();
    contact.setPhoneNumber(object.getPhoneNumber());
    contact.setMailAddress(object.getMailAddress());
    return contact;
  }

  private Address address(AddressObject object) {
    var address = new Address();
    address.setPostalCode(object.getPostalCode());
    address.setPrefectureCode(object.getPrefectureCode());
    address.setPrefectureLabel(object.getPrefectureLabel());
    address.setAddress1(object.getAddress1());
    address.setAddress2(object.getAddress2());
    address.setAddress3(object.getAddress3());
    address.setStreetAddress(object.getStreetAddress());
    address.setBuildingName(object.getBuildingName());
    address.setSupplement(object.getSupplement());
    return address;
  }

  private Hours hours(HoursObject object) {
    var hours = new Hours();
    hours.setOpeningTime(null);
    hours.setClosingTime(null);
    hours.setSupplement(object.getSupplement());
    return hours;
  }

  private Common common(CommonObject object) {
    var common = new Common();
    common.setCreatedDateTime(object.getCreatedDateTime().atOffset(ZoneOffset.ofHours(9)));
    common.setVersion(object.getVersion());
    common.setUpdatedDateTime(
        Optional.ofNullable(object.getUpdatedDateTime())
            .map(ldt -> ldt.atOffset(ZoneOffset.ofHours(9)))
            .orElse(null));
    return common;
  }

}
