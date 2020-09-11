package mabubu0203.com.github.catcafe.domain.repository.cast;

import mabubu0203.com.github.catcafe.domain.entity.cast.CastEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.SearchCondition;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public interface CastRepository {

    CompletableFuture<Stream<CastEntity>> search(SearchCondition condition);

}
