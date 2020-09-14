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
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Cast;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.CastCat;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class CastRepositoryImpl implements CastRepository {

    private final CastSource castSource;
    private final CastCatSource castCatSource;

    @Override
    @Async
    public CompletableFuture<Stream<CastEntity>> search(CastSearchConditions searchConditions) {
        return CompletableFuture.supplyAsync(() -> this.searchImpl(searchConditions).stream());
    }

    private List<CastEntity> searchImpl(CastSearchConditions searchConditions) {

        var castPage = this.castSource.findAll(
                searchConditions.getCastSpecification(),
                searchConditions.getPageRequest());
        var castCatList = this.castCatSource.findAll();

        var casts = new ArrayList<CastEntity>();
        for (Cast cast : castPage) {
            var concatList = castCatList.stream()
                    .filter(castCat -> castCat.getId().equals(cast.getCastCatId()))
                    .map(castCat -> {
                        var castCatEntity = CastCatEntity.builder()
                                .castCatId(Optional.of(new CastCatId(castCat.getId())))
                                .name(castCat.getName())
                                .build();
                        return CastEntity.builder()
                                .castId(Optional.of(new CastId(cast.getId())))
                                .storeId(new StoreId(cast.getStoreId()))
                                .CastCatEntity(castCatEntity)
                                .build();
                    })
                    .collect(Collectors.toList());
            casts.addAll(concatList);
        }
        return casts;
    }

    @Override
    @Async
    public CompletableFuture<CastId> resister(CastEntity cast) {
        return Optional.of(cast)
                .map(this::toDto)
                .map(this.castSource::save)
                .map(dto -> new CastId(dto.getId()))
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
                .map(dto -> new CastCatId(dto.getId()))
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
