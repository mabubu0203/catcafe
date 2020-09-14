package mabubu0203.com.github.catcafe.domain.repository.store.impl;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.domain.repository.store.StoreRepository;
import mabubu0203.com.github.catcafe.domain.value.StoreId;
import mabubu0203.com.github.catcafe.infra.source.jpa.StoreSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepository {

    private final StoreSource source;

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
