package mabubu0203.com.github.catcafe.api.controller.store.helper.response;

import java.time.ZoneOffset;
import java.util.Optional;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.FindResponseMapper;
import org.openapitools.model.Common;
import org.openapitools.model.StoreDetail;
import org.openapitools.model.StoreDetailAddress;
import org.openapitools.model.StoreDetailContact;
import org.openapitools.model.StoreDetailHours;
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
    var common = this.common(store.getCommon());
    detail.setId(store.getId());
    detail.setName(store.getName());
    detail.setContact(this.contact(store.getContact()));
    detail.setAddress(this.address(store.getAddress()));
    detail.setOpenDate(store.getOpenDate());
    detail.setCloseDate(store.getCloseDate());
    detail.setHours(this.hours(store.getHours()));
    detail.setMemo(store.getMemo());
    detail.setCommon(common);
    return detail;
  }

  private StoreDetailContact contact(StoreSearchServiceOutput.ContactObject object) {
    var contact = new StoreDetailContact();
    contact.setPhoneNumber(object.getPhoneNumber());
    contact.setMailAddress(object.getMailAddress());
    return contact;
  }

  private StoreDetailAddress address(StoreSearchServiceOutput.AddressObject object) {
    var address = new StoreDetailAddress();
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

  private StoreDetailHours hours(StoreSearchServiceOutput.HoursObject object) {
    var hours = new StoreDetailHours();
    hours.setOpeningTime(null);
    hours.setClosingTime(null);
    hours.setSupplement(object.getSupplement());
    return hours;
  }

  private Common common(StoreSearchServiceOutput.CommonObject object) {
    var common = new Common();
    common.setCreatedDateTime(object.getCreatedDateTime().atOffset(ZoneOffset.ofHours(9)));
    common.setVersion(object.getVersion());
    Optional.ofNullable(object.getUpdatedDateTime())
        .map(ldt -> ldt.atOffset(ZoneOffset.ofHours(9)))
        .ifPresent(common::setUpdatedDateTime);
    return common;
  }

}
