package mabubu0203.com.github.catcafe.domain.repository.cast;

import java.time.LocalDateTime;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastCatEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastSearchConditions;
import mabubu0203.com.github.catcafe.domain.value.CastCatId;
import mabubu0203.com.github.catcafe.domain.value.CastId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CastRepository {

  /**
   * キャストを複数取得する
   *
   * @param searchConditions
   * @return
   */
  Flux<CastEntity> search(CastSearchConditions searchConditions);

  /**
   * キャストを1件取得する
   *
   * @param cast
   * @param receptionTime
   * @return
   */
  Mono<CastId> resister(CastEntity cast, LocalDateTime receptionTime);

  /**
   * キャスト(猫)を1件取得する
   *
   * @param castCat
   * @param receptionTime
   * @return
   */
  Mono<CastCatId> resister(CastCatEntity castCat, LocalDateTime receptionTime);

}
