package mabubu0203.com.github.catcafe.domain.repository.store;

import mabubu0203.com.github.catcafe.domain.entity.store.StoreEntity;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreSearchConditions;
import mabubu0203.com.github.catcafe.domain.value.StoreId;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public interface StoreRepository {

    CompletableFuture<Stream<StoreEntity>> search(StoreSearchConditions searchConditions);

    @Deprecated
    CompletableFuture<Boolean> exists(StoreId storeId);

    CompletableFuture<StoreId> resister(StoreEntity store);

    CompletableFuture<StoreId> logicalDelete(StoreEntity store, LocalDateTime receptionTime);

}
