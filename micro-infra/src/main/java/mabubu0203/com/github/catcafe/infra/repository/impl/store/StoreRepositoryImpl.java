package mabubu0203.com.github.catcafe.infra.repository.impl.store;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreEntity;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreSearchConditions;
import mabubu0203.com.github.catcafe.domain.repository.store.StoreRepository;
import mabubu0203.com.github.catcafe.domain.value.StoreId;
import mabubu0203.com.github.catcafe.infra.source.jpa.StoreSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Store;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Store_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepository {

  private final StoreSource source;

  @Override
  @Async
  public CompletableFuture<Stream<StoreEntity>> search(StoreSearchConditions searchConditions) {
    var specification = Specification
        .where(this.storeIdInclude(searchConditions.optStoreIds()));
    return this.source.searchStream(specification, searchConditions.getPageRequest())
        .thenApply(stream -> stream.map(this::convertStoreEntity));
  }

  private Specification<Store> storeIdInclude(Optional<List<Integer>> optStoreIds) {
    var storeIds = optStoreIds.orElseGet(Collections::emptyList);
    return storeIds.size() == 0 ?
        null : (root, criteriaQuery, criteriaBuilder) -> root.get(Store_.id).in(storeIds);
  }

  private StoreEntity convertStoreEntity(Store dto) {
    var storeId = new StoreId(dto.getId());
    return StoreEntity.builder()
        .storeId(storeId)
        .name(dto.getName())
        .openingTime(null)
        .closingTime(null)
        .createdDateTime(dto.getCreatedDateTime())
        .version(dto.getVersion())
        .updatedDateTime(dto.getUpdatedDateTime())
        .build();
  }

  @Override
  @Async
  public CompletableFuture<Boolean> exists(StoreId storeId) {
    return CompletableFuture
        .supplyAsync(storeId::intValue)
        .thenApply(this.source::findById)
        .thenApply(Optional::isPresent);
  }

  @Override
  @Async
  public CompletableFuture<StoreId> resister(StoreEntity entity, LocalDateTime receptionTime) {
    return CompletableFuture
        .supplyAsync(() -> entity)
        .thenApply(this::toDto)
        .thenApply(dto -> dto.setCreatedBy(0))
        .thenApply(Store.class::cast)
        .thenCompose(dto -> this.source.insert(dto, receptionTime))
        .thenApply(Store::getId)
        .thenApply(StoreId::new);
  }

  private Store toDto(StoreEntity entity) {
    var storeId = Optional
        .ofNullable(entity.getStoreId())
        .map(StoreId::intValue)
        .orElse(null);
    return new Store()
        .setId(storeId)
        .setName(entity.getName())
        .setOpeningTime(entity.getOpeningTime())
        .setClosingTime(entity.getClosingTime());
  }

  @Override
  @Async
  public CompletableFuture<StoreId> logicalDelete(StoreEntity entity, LocalDateTime receptionTime) {
    return this.findOne(entity)
        .thenCompose(dto -> this.source.logicalDelete(dto, receptionTime))
        .thenApply(Store::getId)
        .thenApply(StoreId::new);
  }

  private CompletableFuture<Store> findOne(StoreEntity entity) {
    var store = (Store) new Store()
        .setId(entity.getStoreId().intValue())
        .setVersion(entity.getVersion())
        .setDeletedFlag(false);
    return this.source.findOne(store)
        .thenApply(opt -> opt.orElseThrow(() -> new RuntimeException("店舗が存在しません")));
  }

}
