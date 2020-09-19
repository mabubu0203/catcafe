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
import mabubu0203.com.github.catcafe.infra.source.jpa.CastViewSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Cast;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.CastCat;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.view.CastView;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.view.CastView_;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class CastRepositoryImpl implements CastRepository {

    private final CastSource castSource;
    private final CastCatSource castCatSource;
    private final CastViewSource castViewSource;

    @Override
    @Async
    public CompletableFuture<Stream<CastEntity>> search(CastSearchConditions searchConditions) {
        var specification = Specification
                .where(this.storeIdInclude(searchConditions.optStoreIds()))
                .and(this.castIdInclude(searchConditions.optCastIds()));
        return CompletableFuture.supplyAsync(() -> this.castViewSource.findAll(specification, searchConditions.getPageRequest()))
                .thenApply(Page::stream)
                .thenApply(stream -> stream.map(this::convertCastEntity));
    }

    private Specification<CastView> storeIdInclude(Optional<List<Integer>> optStoreIds) {
        var storeIds = optStoreIds.orElseGet(Collections::emptyList);
        return storeIds.size() == 0 ?
                null : (root, criteriaQuery, criteriaBuilder) -> root.get(CastView_.storeId).in(storeIds);
    }

    private Specification<CastView> castIdInclude(Optional<List<Integer>> optCastIds) {
        var castIds = optCastIds.orElseGet(Collections::emptyList);
        return castIds.size() == 0 ?
                null : (root, criteriaQuery, criteriaBuilder) -> root.get(CastView_.id).in(castIds);
    }

    private CastEntity convertCastEntity(CastView view) {
        var castId = new CastId(view.getCastId());
        var storeId = new StoreId(view.getStoreId());
        var castCatId = new CastCatId(view.getCastCatId());

        var castCatEntity = CastCatEntity.builder()
                .castCatId(Optional.of(castCatId))
                .name(view.getCastCatName())
                .image(view.getCastCatImage())
                .sex(view.getCastCatSex().name())
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
        return CompletableFuture.supplyAsync(() -> this.toDto(cast))
                .thenApply(dto -> (Cast) dto.setCreatedDateTime(LocalDateTime.now()))
                .thenApply(dto -> (Cast) dto.setCreatedBy(0))
                .thenApply(dto -> (Cast) dto.setVersion(0))
                .thenApply(this.castSource::save)
                .thenApply(Cast::getId)
                .thenApply(CastId::new);
    }

    private Cast toDto(CastEntity entity) {
        return new Cast()
                .setStoreId(entity.getStoreId().intValue())
                .setCastCatId(entity.getCastCatEntity().getCastCatId().get().intValue())
                .setFirstAttendanceDate(null)
                .setLastAttendanceDate(null)
                .setEmploymentStatus(Cast.EmploymentStatus.main)
                .setMemo(entity.getMemo());
    }

    @Override
    public CompletableFuture<Boolean> exists(CastCatId castCatId) {
        return CompletableFuture.supplyAsync(castCatId::intValue)
                .thenApply(this.castCatSource::findById)
                .thenApply(Optional::isPresent);
    }

    @Override
    @Async
    public CompletableFuture<CastCatId> resister(CastCatEntity castCat) {
        return CompletableFuture.supplyAsync(() -> this.toDto(castCat))
                .thenApply(dto -> (CastCat) dto.setCreatedDateTime(LocalDateTime.now()))
                .thenApply(dto -> (CastCat) dto.setCreatedBy(0))
                .thenApply(dto -> (CastCat) dto.setVersion(0))
                .thenApply(this.castCatSource::save)
                .thenApply(CastCat::getId)
                .thenApply(CastCatId::new);
    }

    private CastCat toDto(CastCatEntity entity) {
        return new CastCat()
                .setName(entity.getName())
                .setImage(entity.getImage())
                .setType(null)
                .setSex(CastCat.Sex.male)
                .setBirthdayDate(null)
                .setMemo(null);
    }

}
