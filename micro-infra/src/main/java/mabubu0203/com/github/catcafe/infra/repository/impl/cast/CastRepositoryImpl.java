package mabubu0203.com.github.catcafe.infra.repository.impl.cast;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastCatEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastSearchConditions;
import mabubu0203.com.github.catcafe.domain.repository.cast.CastRepository;
import mabubu0203.com.github.catcafe.domain.value.CastCatId;
import mabubu0203.com.github.catcafe.domain.value.CastId;
import mabubu0203.com.github.catcafe.domain.value.StoreId;
import mabubu0203.com.github.catcafe.infra.source.jpa.CastCatSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.CastSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.projection.CastCatProjection;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Cast;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.CastCat;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class CastRepositoryImpl implements CastRepository {

    private final CastSource castSource;
    private final CastCatSource castCatSource;

    @Override
    @Async
    public CompletableFuture<Stream<CastEntity>> search(CastSearchConditions searchConditions) {
        return this.castSource.getPage(searchConditions.getPageRequest())
                .thenApply(Page::stream)
                .thenApply(stream -> stream.map(this::convertCastEntity));
    }

    private CastEntity convertCastEntity(CastCatProjection projection) {
        var castId = new CastId(projection.getCastId());
        var storeId = new StoreId(projection.getStoreId());
        var castCatId = new CastCatId(projection.getCastCatId());

        var castCatEntity = CastCatEntity.builder()
                .castCatId(Optional.of(castCatId))
                .name(projection.getCastCatName())
                .build();
        return CastEntity.builder()
                .castId(Optional.of(castId))
                .storeId(storeId)
                .CastCatEntity(castCatEntity)
                .build();
    }

    @Override
    @Async
    public CompletableFuture<CastId> resister(CastEntity cast) {
        return CompletableFuture.supplyAsync(() -> this.castSource.save(this.toDto(cast)))
                .thenApply(Cast::getId)
                .thenApply(CastId::new);
    }

    private Cast toDto(CastEntity entity) {
        var dto = new Cast()
                .setStoreId(entity.getStoreId().intValue())
                .setCastCatId(entity.getCastCatEntity().getCastCatId().get().intValue());
        dto
                .setCreatedDateTime(LocalDateTime.now())
                .setCreatedBy(0)
                .setVersion(0);
        return dto;
    }

    @Override
    public CompletableFuture<Boolean> exists(CastCatId castCatId) {
        return CompletableFuture.supplyAsync(() -> this.castCatSource.findById(castCatId.intValue()))
                .thenApply(Optional::isPresent);
    }

    @Override
    @Async
    public CompletableFuture<CastCatId> resister(CastCatEntity castCat) {
        return CompletableFuture.supplyAsync(() -> this.castCatSource.save(this.toDto(castCat)))
                .thenApply(CastCat::getId)
                .thenApply(CastCatId::new);
    }

    private CastCat toDto(CastCatEntity entity) {
        var dto = new CastCat()
                .setName(entity.getName());
        dto
                .setCreatedDateTime(LocalDateTime.now())
                .setCreatedBy(0)
                .setVersion(0);
        return dto;
    }

}
