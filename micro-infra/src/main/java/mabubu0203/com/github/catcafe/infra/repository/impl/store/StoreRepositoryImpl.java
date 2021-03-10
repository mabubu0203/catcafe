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
import mabubu0203.com.github.catcafe.domain.value.StoreId;
import mabubu0203.com.github.catcafe.infra.source.r2dbc.StoreSource;
import mabubu0203.com.github.catcafe.infra.source.r2dbc.dto.table.Store;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepository {

  private final StoreSource source;

  @Override
  public Flux<StoreEntity> search(StoreSearchConditions searchConditions) {
    Predicate<Store> storeIdInclude = store -> {
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
        .map(e -> this.attach(new Store(), e))
        .map(dto -> dto.setCreatedBy(0))
        .map(Store.class::cast)
        .map(dto -> this.source.insert(dto, receptionTime))
        .orElseThrow(RuntimeException::new)
        .map(Store::getId)
        .map(StoreId::new);
  }

  @Override
  public Mono<StoreId> modify(StoreEntity entity, LocalDateTime receptionTime) {
    return Optional.of(entity)
        .map(StoreEntity::getStoreId)
        .map(this::findDto)
        .orElseThrow(RuntimeException::new)
        .map(dto -> this.attach(dto, entity))
        .map(dto -> (Store) dto.setVersion(entity.getVersion()))
        .flatMap(dto -> this.source.update(dto, receptionTime))
        .map(Store::getId)
        .map(StoreId::new);
  }

  @Override
  public Mono<StoreId> logicalDelete(StoreEntity entity, LocalDateTime receptionTime) {
    return Optional.of(entity)
        .map(StoreEntity::getStoreId)
        .map(this::findDto)
        .orElseThrow(RuntimeException::new)
        .map(dto -> (Store) dto.setVersion(entity.getVersion()))
        .flatMap(dto -> this.source.logicalDelete(dto, receptionTime))
        .map(Store::getId)
        .map(StoreId::new);
  }

  private StoreEntity convertStoreEntity(Store dto) {
    var storeId = new StoreId(dto.getId());
    return StoreEntity.builder()
        .storeId(storeId)
        .name(dto.getName())
        .openingTime(dto.getOpeningTime())
        .closingTime(dto.getClosingTime())
        .createdDateTime(dto.getCreatedDateTime())
        .version(dto.getVersion())
        .updatedDateTime(dto.getUpdatedDateTime())
        .build();
  }

  private Mono<Store> findDto(StoreId storeId) {
    return this.source.findById(storeId.intValue())
        .filter(BaseTable::isExists)
        // 404で返却するためのエラーを検討
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("店舗が存在しません")));
  }

  private Store attach(Store dto, StoreEntity entity) {
    var storeId = Optional.of(entity)
        .map(StoreEntity::getStoreId)
        .map(StoreId::intValue)
        .orElse(null);
    return Optional.of(dto)
        .orElse(new Store())
        .setId(storeId)
        .setName(entity.getName())
        .setOpeningTime(entity.getOpeningTime())
        .setClosingTime(entity.getClosingTime());
  }

}
