package mabubu0203.com.github.catcafe.domain.repository.store;

import mabubu0203.com.github.catcafe.domain.value.StoreId;

import java.util.concurrent.CompletableFuture;

public interface StoreRepository {

    CompletableFuture<Boolean> exists(StoreId storeId);

}
