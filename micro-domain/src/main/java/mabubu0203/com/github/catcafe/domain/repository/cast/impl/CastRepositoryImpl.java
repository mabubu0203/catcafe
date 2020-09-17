package mabubu0203.com.github.catcafe.domain.repository.cast.impl;

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
import java.util.ArrayList;
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
        return Optional.of(searchConditions)
                .map(conditions -> this.castSource.getPage(conditions.getPageRequest()))
                .map(feature -> feature.thenApply(this::convertDomain))
                .orElseGet(() -> CompletableFuture.completedFuture(Stream.empty()));
    }

    private Stream<CastEntity> convertDomain(Page<CastCatProjection> page) {
        var casts = new ArrayList<CastEntity>();
        for (CastCatProjection cast : page) {
            casts.add(this.convertCastEntity(cast));
        }
        return casts.stream();
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
        return Optional.of(cast)
                .map(this::toDto)
                .map(this.castSource::save)
                .map(Cast::getId)
                .map(CastId::new)
                .map(castId -> CompletableFuture.supplyAsync(() -> castId))
                .get();
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
        return Optional.of(castCatId)
                .map(CastCatId::intValue)
                .map(this.castCatSource::findById)
                .map(Optional::isPresent)
                .map(bool -> CompletableFuture.supplyAsync(() -> bool))
                .get();
    }

    @Override
    @Async
    public CompletableFuture<CastCatId> resister(CastCatEntity castCat) {
        return Optional.of(castCat)
                .map(this::toDto)
                .map(this.castCatSource::save)
                .map(CastCat::getId)
                .map(CastCatId::new)
                .map(castCatId -> CompletableFuture.supplyAsync(() -> castCatId))
                .get();
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
