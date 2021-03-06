package mabubu0203.com.github.catcafe.domain.repository.store;

import java.time.LocalDateTime;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreEntity;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreSearchConditions;
import mabubu0203.com.github.catcafe.domain.value.StoreId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StoreRepository {

  Flux<StoreEntity> search(StoreSearchConditions searchConditions);

  Mono<StoreEntity> findBy(StoreId storeId);

  Mono<StoreId> resister(StoreEntity store, LocalDateTime receptionTime);

  Mono<StoreId> modify(StoreEntity store, LocalDateTime receptionTime);

  Mono<StoreId> logicalDelete(StoreEntity store, LocalDateTime receptionTime);

}
