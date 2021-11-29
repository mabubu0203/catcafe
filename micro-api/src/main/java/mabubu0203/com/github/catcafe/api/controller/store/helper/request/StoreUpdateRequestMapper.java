package mabubu0203.com.github.catcafe.api.controller.store.helper.request;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreModifyServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.UpdateRequestMapper;
import org.openapitools.model.StoreUpdateRequest;

@RequiredArgsConstructor
public class StoreUpdateRequestMapper implements
    UpdateRequestMapper<StoreUpdateRequest, StoreModifyServiceInput> {

  private final String cats;
  private final Integer storeId;

  @Override
  public StoreModifyServiceInput apply(StoreUpdateRequest request) {
    var contact = request.getContact();
    var contactObject = StoreModifyServiceInput.ContactObject.builder()
        .phoneNumber(contact.getPhoneNumber())
        .mailAddress(contact.getMailAddress())
        .build();
    var address = request.getAddress();
    var addressObject = StoreModifyServiceInput.AddressObject.builder()
        .postalCode(address.getPostalCode())
        .prefectureCode(address.getPrefectureCode())
        .address1(address.getAddress1())
        .address2(address.getAddress2())
        .address3(address.getAddress3())
        .streetAddress(address.getStreetAddress())
        .buildingName(address.getBuildingName())
        .supplement(address.getSupplement())
        .build();
    var hours = request.getHours();
    var hoursObject = StoreModifyServiceInput.HoursObject.builder()
        .openingTime(LocalTime.parse(hours.getOpeningTime(), DateTimeFormatter.ofPattern("HH:mm")))
        .closingTime(LocalTime.parse(hours.getClosingTime(), DateTimeFormatter.ofPattern("HH:mm")))
        .supplement(hours.getSupplement())
        .build();

    return StoreModifyServiceInput.builder()
        .cats(this.cats)
        .storeId(this.storeId)
        .name(request.getName())
        .contact(contactObject)
        .address(addressObject)
        .openDate(request.getOpenDate())
        .closeDate(request.getCloseDate())
        .hours(hoursObject)
        .memo(request.getMemo())
        .version(request.getVersion())
        .build();
  }

}
