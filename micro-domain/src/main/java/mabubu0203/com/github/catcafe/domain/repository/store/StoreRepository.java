package mabubu0203.com.github.catcafe.domain.repository.store;

import java.time.LocalDateTime;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreEntity;
import mabubu0203.com.github.catcafe.domain.entity.store.StoreSearchConditions;
import mabubu0203.com.github.catcafe.domain.value.StoreId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StoreRepository {

  /**
   * 店舗を複数取得する
   * @param searchConditions
   * @return
   */
  Flux<StoreEntity> search(StoreSearchConditions searchConditions);

  /**
   * 店舗を1件取得する
   * @param storeId
   * @return
   */
  Mono<StoreEntity> findBy(StoreId storeId);

  /**
   * 店舗を1件登録する
   * @param store
   * @param receptionTime
   * @return
   */
  Mono<StoreId> resister(StoreEntity store, LocalDateTime receptionTime);

  /**
   * 店舗を1件更新する
   * @param store
   * @param receptionTime
   * @return
   */
  Mono<StoreId> modify(StoreEntity store, LocalDateTime receptionTime);

  /**
   * 店舗を1件削除する
   * @param store
   * @param receptionTime
   * @return
   */
  Mono<StoreId> logicalDelete(StoreEntity store, LocalDateTime receptionTime);

}
