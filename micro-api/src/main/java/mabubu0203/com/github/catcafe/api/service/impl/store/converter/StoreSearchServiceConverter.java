package mabubu0203.com.github.catcafe.api.service.impl.store.converter;

import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreSearchServiceOutput;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreSearchServiceOutput.StoreObject;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreSearchServiceOutput.StoreSearchServiceOutputBuilder;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreEntity;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreSearchConditions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class StoreSearchServiceConverter {

  public StoreSearchConditions toSearchCondition(StoreSearchServiceInput input) {
    return new StoreSearchConditions(
        input.getOptPage().orElse(0),
        input.getOptSize().orElse(20),
        input.getOptSortKeys()
    )
        .optStoreIds(input.getOptStoreIds());
  }

  public Mono<StoreSearchServiceOutput> toOutput(Flux<StoreEntity> flux) {
    return flux.map(this::toStoreObject)
        .collectList()
        .map(stores -> StoreSearchServiceOutput.builder().stores(stores))
        .map(StoreSearchServiceOutputBuilder::build);
  }

  private StoreObject toStoreObject(StoreEntity storeEntity) {
    return
        StoreSearchServiceOutput.StoreObject.builder()
            .id(storeEntity.getStoreIdValue())
            .name(storeEntity.getName())
            .contact(
                StoreSearchServiceOutput.ContactObject.builder()
                    .phoneNumber(storeEntity.getPhoneNumberValue())
                    .mailAddress(storeEntity.getMailAddressValue())
                    .build()
            )
            .address(
                StoreSearchServiceOutput.AddressObject.builder()
                    .postalCode(storeEntity.getPostalCodeValue())
                    .prefectureCode(storeEntity.getPrefectureCode())
                    .prefectureLabel(storeEntity.getPrefectureLabel())
                    .address1(storeEntity.getAddress1())
                    .address2(storeEntity.getAddress2())
                    .address3(storeEntity.getAddress3())
                    .streetAddress(storeEntity.getStreetAddress())
                    .buildingName(storeEntity.getBuildingName())
                    .supplement(storeEntity.getAddressSupplement())
                    .build())
            .openDate(storeEntity.getOpenDate())
            .closeDate(storeEntity.getCloseDate())
            .hours(
                StoreSearchServiceOutput.HoursObject.builder()
                    .openingTime(storeEntity.getOpeningTime())
                    .closingTime(storeEntity.getClosingTime())
                    .supplement(storeEntity.getHoursSupplement())
                    .build())
            .memo(storeEntity.getMemoValue())
            .common(
                StoreSearchServiceOutput.CommonObject.builder()
                    .createdDateTime(storeEntity.getCreatedDateTime())
                    .version(storeEntity.getVersion())
                    .updatedDateTime(storeEntity.getUpdatedDateTime())
                    .build())
            .build();
  }

}
