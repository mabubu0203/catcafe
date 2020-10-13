package mabubu0203.com.github.catcafe.infra.repository.impl.store;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreEntity;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreSearchConditions;
import mabubu0203.com.github.catcafe.domain.repository.store.StoreRepository;
import mabubu0203.com.github.catcafe.domain.value.StoreId;
import mabubu0203.com.github.catcafe.infra.source.jpa.StoreSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Store;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Store_;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepository {

    private final StoreSource source;

    @Override
    @Async
    public CompletableFuture<Stream<StoreEntity>> search(StoreSearchConditions searchConditions) {
        var specification = Specification
                .where(this.storeIdInclude(searchConditions.optStoreIds()));
        return CompletableFuture
                .supplyAsync(() ->
                        this.source.findAll(specification, searchConditions.getPageRequest()))
                .thenApply(Page::stream)
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
                .thenApply(dto -> this.source.insert(dto, receptionTime))
                .thenApply(Store::getId)
                .thenApply(StoreId::new);
    }

    private Store toDto(StoreEntity entity) {
        var storeId = Optional.ofNullable(entity.getStoreId())
                .map(StoreId::intValue)
                .orElse(null);
        return new Store()
                .setId(storeId)
                .setName(entity.getName())
                .setOpeningTime(entity.getOpeningTime())
                .setClosingTime(entity.getOpeningTime());
    }

    @Override
    @Async
    public CompletableFuture<StoreId> logicalDelete(StoreEntity entity, LocalDateTime receptionTime) {
        return CompletableFuture
                .supplyAsync(() -> (Store) new Store()
                        .setId(entity.getStoreId().intValue())
                        .setVersion(entity.getVersion())
                        .setDeletedFlag(false))
                .thenApply(Example::of)
                .thenApply(this.source::findOne)
                .thenApply(opt -> opt.orElseThrow(RuntimeException::new))
                .thenApply(dto -> this.source.logicalDelete(dto, receptionTime))
                .thenApply(Store::getId)
                .thenApply(StoreId::new);
    }

}
