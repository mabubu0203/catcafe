package mabubu0203.com.github.catcafe.api.service.impl.store.converter;

import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreModifyServiceInput;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreModifyServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreEntity;
import mabubu0203.com.github.catcafe.domain.value.MailAddress;
import mabubu0203.com.github.catcafe.domain.value.Memo;
import mabubu0203.com.github.catcafe.domain.value.PhoneNumber;
import mabubu0203.com.github.catcafe.domain.value.PostalCode;
import mabubu0203.com.github.catcafe.domain.value.Prefecture;
import mabubu0203.com.github.catcafe.domain.value.StoreId;
import mabubu0203.com.github.catcafe.domain.value.Supplement;

public class StoreModifyServiceConverter {

  public StoreEntity fromInput(StoreModifyServiceInput input) {
    var contactObject = input.getContact();
    var addressObject = input.getAddress();
    var hoursObject = input.getHours();
    var storeId = new StoreId(input.getStoreId());
    var phoneNumber = new PhoneNumber(contactObject.getPhoneNumber());
    var mailAddress = new MailAddress(contactObject.getMailAddress());
    var postalCode = new PostalCode(addressObject.getPostalCode());
    var prefecture = Prefecture.getByCode(addressObject.getPrefectureCode());
    var addressSupplement = new Supplement(addressObject.getSupplement());
    var hoursSupplement = new Supplement(hoursObject.getSupplement());
    var memo = new Memo(input.getMemo());
    return StoreEntity.builder()
        .storeId(storeId)
        .name(input.getName())
        .phoneNumber(phoneNumber)
        .mailAddress(mailAddress)
        .postalCode(postalCode)
        .prefecture(prefecture)
        .address1(addressObject.getAddress1())
        .address2(addressObject.getAddress2())
        .address3(addressObject.getAddress3())
        .streetAddress(addressObject.getStreetAddress())
        .buildingName(addressObject.getBuildingName())
        .addressSupplement(addressSupplement)
        .openDate(input.getOpenDate())
        .closeDate(input.getCloseDate())
        .openingTime(hoursObject.getOpeningTime())
        .closingTime(hoursObject.getClosingTime())
        .hoursSupplement(hoursSupplement)
        .memo(memo)
        .createdDateTime(null)
        .version(input.getVersion())
        .updatedDateTime(null)
        .build();
  }

  public StoreModifyServiceOutput toOutput(StoreId storeId) {
    return StoreModifyServiceOutput.builder()
        .id(storeId.value())
        .build();
  }

}
