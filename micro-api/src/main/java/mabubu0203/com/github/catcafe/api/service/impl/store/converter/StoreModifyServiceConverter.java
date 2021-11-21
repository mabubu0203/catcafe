package mabubu0203.com.github.catcafe.api.service.impl.store.converter;

import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreModifyServiceInput;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreModifyServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreEntity;
import mabubu0203.com.github.catcafe.domain.value.MailAddress;
import mabubu0203.com.github.catcafe.domain.value.Memo;
import mabubu0203.com.github.catcafe.domain.value.PhoneNumber;
import mabubu0203.com.github.catcafe.domain.value.PostalCode;
import mabubu0203.com.github.catcafe.domain.value.StoreId;

public class StoreModifyServiceConverter {

  public StoreEntity fromInput(StoreModifyServiceInput input) {
    var storeId = new StoreId(input.getStoreId());
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
