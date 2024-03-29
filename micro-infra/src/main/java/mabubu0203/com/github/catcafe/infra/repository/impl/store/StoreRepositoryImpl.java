package mabubu0203.com.github.catcafe.infra.repository.impl.store;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.common.exception.ResourceNotFoundException;
import mabubu0203.com.github.catcafe.common.source.r2dbc.dto.BaseTable;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreEntity;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreSearchConditions;
import mabubu0203.com.github.catcafe.domain.repository.store.StoreRepository;
import mabubu0203.com.github.catcafe.domain.value.MailAddress;
import mabubu0203.com.github.catcafe.domain.value.Memo;
import mabubu0203.com.github.catcafe.domain.value.PhoneNumber;
import mabubu0203.com.github.catcafe.domain.value.PostalCode;
import mabubu0203.com.github.catcafe.domain.value.Prefecture;
import mabubu0203.com.github.catcafe.domain.value.StoreId;
import mabubu0203.com.github.catcafe.domain.value.Supplement;
import mabubu0203.com.github.catcafe.infra.source.r2dbc.StoreSource;
import mabubu0203.com.github.catcafe.infra.source.r2dbc.dto.table.StoreTable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepository {

  private final StoreSource source;

  @Override
  public Flux<StoreEntity> search(StoreSearchConditions searchConditions) {
    Predicate<StoreTable> storeIdInclude = store -> {
      var storeIds = searchConditions.optStoreIds().orElseGet(Collections::emptyList);
      return storeIds.size() == 0 || storeIds.contains(store.getId());
    };
    return this.source.findAll()
        .filter(BaseTable::isExists)
        .filter(storeIdInclude)
        .map(this::convertStoreEntity);
  }

  @Override
  public Mono<StoreEntity> findBy(StoreId storeId) {
    return this.findDto(storeId)
        .map(this::convertStoreEntity);
  }

  @Override
  public Mono<StoreId> resister(StoreEntity entity, LocalDateTime receptionTime) {
    return Optional.of(entity)
        .map(this::attach)
        .map(dto -> dto.setCreatedBy(0))
        .map(StoreTable.class::cast)
        .map(dto -> this.source.insert(dto, receptionTime))
        .orElseThrow(RuntimeException::new)
        .mapNotNull(StoreTable::getId)
        .map(StoreId::new);
  }

  @Override
  public Mono<StoreId> modify(StoreEntity entity, LocalDateTime receptionTime) {
    return this.findDto(entity.getStoreId())
        .map(dto -> this.attach(dto, entity))
        .map(dto -> dto.setVersion(entity.getVersion()))
        .cast(StoreTable.class)
        .flatMap(dto -> this.source.update(dto, receptionTime))
        .mapNotNull(StoreTable::getId)
        .map(StoreId::new);
  }

  @Override
  public Mono<StoreId> logicalDelete(StoreEntity entity, LocalDateTime receptionTime) {
    return this.findDto(entity.getStoreId())
        .map(dto -> dto.setVersion(entity.getVersion()))
        .cast(StoreTable.class)
        .flatMap(dto -> this.source.logicalDelete(dto, receptionTime))
        .mapNotNull(StoreTable::getId)
        .map(StoreId::new);
  }

  private StoreEntity convertStoreEntity(StoreTable dto) {
    var storeId = new StoreId(dto.getId());
    var phoneNumber = new PhoneNumber(dto.getPhoneNumber());
    var mailAddress = new MailAddress(dto.getMailAddress());
    var postalCode = new PostalCode(dto.getPostalCode());
    var prefecture = Prefecture.getByCode(dto.getPrefectureCode());
    var addressSupplement = new Supplement(dto.getAddressSupplement());
    var hoursSupplement = new Supplement(dto.getHoursSupplement());
    var memo = new Memo(dto.getMemo());
    return StoreEntity.builder()
        .storeId(storeId)
        .name(dto.getName())
        .phoneNumber(phoneNumber)
        .mailAddress(mailAddress)
        .postalCode(postalCode)
        .prefecture(prefecture)
        .address1(dto.getAddress1())
        .address2(dto.getAddress2())
        .address3(dto.getAddress3())
        .streetAddress(dto.getStreetAddress())
        .buildingName(dto.getBuildingName())
        .addressSupplement(addressSupplement)
        .openDate(dto.getOpenDate())
        .closeDate(dto.getCloseDate())
        .openingTime(dto.getOpeningTime())
        .closingTime(dto.getClosingTime())
        .hoursSupplement(hoursSupplement)
        .memo(memo)
        .createdDateTime(dto.getCreatedDateTime())
        .version(dto.getVersion())
        .updatedDateTime(dto.getUpdatedDateTime())
        .build();
  }

  private Mono<StoreTable> findDto(StoreId storeId) {
    return this.source.findById(storeId.value())
        .filter(BaseTable::isExists)
        // 404で返却するためのエラーを検討
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("店舗が存在しません")));
  }

  private StoreTable attach(StoreEntity entity) {
    return this.attach(null, entity);
  }

  private StoreTable attach(StoreTable dto, StoreEntity entity) {
    return Optional.ofNullable(dto)
        .orElse(new StoreTable())
        .setId(entity.getStoreIdValue())
        .setName(entity.getName())
        .setPhoneNumber(entity.getPhoneNumberValue())
        .setMailAddress(entity.getMailAddressValue())
        .setPostalCode(entity.getPostalCodeValue())
        .setPrefectureCode(entity.getPrefectureCode())
        .setAddress1(entity.getAddress1())
        .setAddress2(entity.getAddress2())
        .setAddress3(entity.getAddress3())
        .setStreetAddress(entity.getStreetAddress())
        .setBuildingName(entity.getBuildingName())
        .setAddressSupplement(entity.getAddressSupplementValue())
        .setOpenDate(entity.getOpenDate())
        .setCloseDate(entity.getCloseDate())
        .setOpeningTime(entity.getOpeningTime())
        .setClosingTime(entity.getClosingTime())
        .setHoursSupplement(entity.getHoursSupplementValue())
        .setMemo(entity.getMemoValue());
  }

}
