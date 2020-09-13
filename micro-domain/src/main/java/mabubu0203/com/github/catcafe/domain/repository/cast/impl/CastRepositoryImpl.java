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
        var castDto = new Cast()
                .setStoreId(cast.getStoreId().intValue())
                .setCastCatId(cast.getCastCatEntity().getCastCatId().get().intValue());
        castDto
                .setCreatedDateTime(LocalDateTime.now())
                .setCreatedBy(0)
                .setVersion(0);
        castDto = this.castSource.save(castDto);
        return CompletableFuture.supplyAsync(() -> null);
    }

    @Override
    @Async
    public CompletableFuture<CastCatId> resister(CastCatEntity castCat) {
        var castCatDto = new CastCat()
                .setName(castCat.getName());
        castCatDto
                .setCreatedDateTime(LocalDateTime.now())
                .setCreatedBy(0)
                .setVersion(0);
        castCatDto = this.castCatSource.save(castCatDto);
        return CompletableFuture.supplyAsync(() -> null);
    }

}
