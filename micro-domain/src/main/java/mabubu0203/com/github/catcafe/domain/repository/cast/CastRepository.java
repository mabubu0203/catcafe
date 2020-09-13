package mabubu0203.com.github.catcafe.domain.repository.cast;

import mabubu0203.com.github.catcafe.domain.entity.cast.CastCatEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastSearchConditions;
import mabubu0203.com.github.catcafe.domain.value.CastCatId;
import mabubu0203.com.github.catcafe.domain.value.CastId;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public interface CastRepository {

    CompletableFuture<Stream<CastEntity>> search(CastSearchConditions searchConditions);

    CompletableFuture<CastId> resister(CastEntity cast);

    CompletableFuture<CastCatId> resister(CastCatEntity castCat);

}
