package mabubu0203.com.github.catcafe.infra.repository.impl.store;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreEntity;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreSearchConditions;
import mabubu0203.com.github.catcafe.domain.repository.store.StoreRepository;
import mabubu0203.com.github.catcafe.domain.value.StoreId;
import mabubu0203.com.github.catcafe.infra.source.jpa.StoreSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Store;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Store_;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Collections;
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
        return CompletableFuture.supplyAsync(() -> this.source.findAll(
//                searchConditions.getCastSpecification(),
                this.getCastSpecification(),
                searchConditions.getPageRequest()))
                .thenApply(Page::stream)
                .thenApply(stream -> stream.map(this::convertStoreEntity));
    }

    public Specification<Store> getCastSpecification() {
        return Specification
                .where(this.storeIdInclude());
    }

    private Specification<Store> storeIdInclude() {
        return (Specification<Store>) (root, criteriaQuery, criteriaBuilder) -> root.get(Store_.id).in(Collections.emptyList());
    }

    private StoreEntity convertStoreEntity(Store store) {
        return StoreEntity.builder()
                .storeId(Optional.of(new StoreId(store.getId())))
                .name(store.getName())
                .build();
    }

    @Override
    @Async
    public CompletableFuture<Boolean> exists(StoreId storeId) {
        return CompletableFuture.supplyAsync(() -> this.source.findById(storeId.intValue()))
                .thenApply(Optional::isPresent);
    }

    @Override
    @Async
    public CompletableFuture<StoreId> resister(StoreEntity store) {
        return CompletableFuture.supplyAsync(() -> this.source.save(this.toDto(store)))
                .thenApply(Store::getId)
                .thenApply(StoreId::new);
    }

    private Store toDto(StoreEntity entity) {
        var dto = new Store()
                .setName(entity.getName());
        dto
                .setCreatedDateTime(LocalDateTime.now())
                .setCreatedBy(0)
                .setVersion(0);
        return dto;
    }

}