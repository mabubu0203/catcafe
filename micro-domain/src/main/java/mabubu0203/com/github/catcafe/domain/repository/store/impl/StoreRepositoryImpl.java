package mabubu0203.com.github.catcafe.domain.repository.store.impl;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreEntity;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreSearchConditions;
import mabubu0203.com.github.catcafe.domain.repository.store.StoreRepository;
import mabubu0203.com.github.catcafe.domain.value.StoreId;
import mabubu0203.com.github.catcafe.infra.source.jpa.StoreSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Store;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepository {

    private final StoreSource source;

    @Override
    public CompletableFuture<Stream<StoreEntity>> search(StoreSearchConditions searchConditions) {
        return CompletableFuture.supplyAsync(() -> this.searchImpl(searchConditions).stream());
    }

    private List<StoreEntity> searchImpl(StoreSearchConditions searchConditions) {
        var storePage = this.source.findAll(
                searchConditions.getCastSpecification(),
                searchConditions.getPageRequest());

        var stores = new ArrayList<StoreEntity>();
        for (Store store : storePage) {
            stores.add(StoreEntity.builder()
                    .storeId(Optional.of(new StoreId(store.getId())))
                    .name(store.getName())
                    .build());
        }
        return stores;
    }

    @Override
    public CompletableFuture<Boolean> exists(StoreId storeId) {
        return Optional.of(storeId)
                .map(StoreId::intValue)
                .map(this.source::findById)
                .map(Optional::isPresent)
                .map(bool -> CompletableFuture.supplyAsync(() -> bool))
                .get();
    }

}
