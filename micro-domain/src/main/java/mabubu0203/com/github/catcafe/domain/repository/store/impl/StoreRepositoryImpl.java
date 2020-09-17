package mabubu0203.com.github.catcafe.domain.repository.store.impl;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreEntity;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreSearchConditions;
import mabubu0203.com.github.catcafe.domain.repository.store.StoreRepository;
import mabubu0203.com.github.catcafe.domain.value.StoreId;
import mabubu0203.com.github.catcafe.infra.source.jpa.StoreSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Store;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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
                searchConditions.getCastSpecification(),
                searchConditions.getPageRequest()))
                .thenApply(Page::stream)
                .thenApply(stream -> stream.map(this::convertStoreEntity));
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
