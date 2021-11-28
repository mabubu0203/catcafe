package mabubu0203.com.github.catcafe.api.controller.store.helper.request;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreRegisterServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.CreateRequestMapper;
import org.openapitools.model.StoreCreateRequest;

@RequiredArgsConstructor
public class StoreCreateRequestMapper implements
    CreateRequestMapper<StoreCreateRequest, StoreRegisterServiceInput> {

  private final String cats;

  @Override
  public StoreRegisterServiceInput apply(StoreCreateRequest request) {
    var contact = request.getContact();
    var contactObject = StoreRegisterServiceInput.ContactObject.builder()
        .phoneNumber(contact.getPhoneNumber())
        .mailAddress(contact.getMailAddress())
        .build();
    var address = request.getAddress();
    var addressObject = StoreRegisterServiceInput.AddressObject.builder()
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
    var hoursObject = StoreRegisterServiceInput.HoursObject.builder()
        .openingTime(LocalTime.parse(hours.getOpeningTime(), DateTimeFormatter.ofPattern("HH:mm")))
        .closingTime(LocalTime.parse(hours.getClosingTime(), DateTimeFormatter.ofPattern("HH:mm")))
        .supplement(hours.getSupplement())
        .build();

    return StoreRegisterServiceInput.builder()
        .cats(this.cats)
        .name(request.getName())
        .contact(contactObject)
        .address(addressObject)
        .openDate(request.getOpenDate())
        .closeDate(request.getCloseDate())
        .hours(hoursObject)
        .memo(request.getMemo())
        .build();
  }

}
