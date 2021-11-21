package mabubu0203.com.github.catcafe.api.service.impl.store.converter;

import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreRegisterServiceInput;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreRegisterServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreEntity;
import mabubu0203.com.github.catcafe.domain.value.MailAddress;
import mabubu0203.com.github.catcafe.domain.value.Memo;
import mabubu0203.com.github.catcafe.domain.value.PhoneNumber;
import mabubu0203.com.github.catcafe.domain.value.PostalCode;
import mabubu0203.com.github.catcafe.domain.value.StoreId;

public class StoreRegisterServiceConverter {

  public StoreEntity fromInput(StoreRegisterServiceInput input) {
    var storeId = StoreId.emptyId();
    var phoneNumber = new PhoneNumber(null);
    var mailAddress = new MailAddress(null);
    var postalCode = new PostalCode(null);
    var memo = new Memo(null);
    return StoreEntity.builder()
        .storeId(storeId)
        .name(input.getName())
        .phoneNumber(phoneNumber)
        .mailAddress(mailAddress)
        .postalCode(postalCode)
        .prefectureCode(null)
        .address1(null)
        .address2(null)
        .address3(null)
        .streetAddress(null)
        .buildingName(null)
        .addressSupplement(null)
        .openingTime(null)
        .closingTime(null)
        .hoursSupplement(null)
        .memo(memo)
        .createdDateTime(null)
        .version(null)
        .updatedDateTime(null)
        .build();
  }

  public StoreRegisterServiceOutput toOutput(StoreId storeId) {
    return StoreRegisterServiceOutput.builder()
        .id(storeId.value())
        .build();
  }

}
