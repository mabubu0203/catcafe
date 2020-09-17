package mabubu0203.com.github.catcafe.infra.source.jpa;

import mabubu0203.com.github.catcafe.common.source.jpa.TableSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.projection.CastCatProjection;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Cast;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

@Repository
public interface CastSource extends TableSource<Cast, Integer> {

    @Query(nativeQuery = true, name = "casts_page")
    CompletableFuture<Page<CastCatProjection>> getPage(Pageable pageable);

}
