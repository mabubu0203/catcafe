package mabubu0203.com.github.catcafe.domain.repository.cast;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastCatEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastSearchConditions;
import mabubu0203.com.github.catcafe.domain.value.CastCatId;
import mabubu0203.com.github.catcafe.domain.value.CastId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CastRepository {

  Flux<CastEntity> search(CastSearchConditions searchConditions);

  Mono<CastId> resister(CastEntity cast, LocalDateTime receptionTime);

  Mono<CastCatId> resister(CastCatEntity castCat, LocalDateTime receptionTime);

}
