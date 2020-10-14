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
import org.springframework.data.domain.Example;
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
        return CompletableFuture
                .supplyAsync(() ->
                        this.castViewSource.findAll(specification, searchConditions.getPageRequest()))
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

    private CastEntity convertCastEntity(CastView dto) {
        var castId = new CastId(dto.getCastId());
        var storeId = new StoreId(dto.getStoreId());
        var castCatId = new CastCatId(dto.getCastCatId());

        var castCatEntity = CastCatEntity.builder()
                .castCatId(castCatId)
                .name(dto.getCastCatName())
                .image(dto.getCastCatImage())
                .sex(dto.getCastCatSex().name())
                .createdDateTime(null)
                .version(null)
                .updatedDateTime(null)
                .build();
        return CastEntity.builder()
                .castId(castId)
                .storeId(storeId)
                .createdDateTime(null)
                .version(null)
                .updatedDateTime(null)
                .CastCatEntity(castCatEntity)
                .build();
    }

    @Override
    @Async
    public CompletableFuture<CastId> resister(CastEntity entity, LocalDateTime receptionTime) {
        return this.findOne(entity.getCastCatEntity())
                .thenApply(dto -> entity)
                .thenApply(this::toDto)
                .thenApply(dto -> dto.setCreatedBy(0))
                .thenApply(Cast.class::cast)
                .thenApply(dto -> this.castSource.insert(dto, receptionTime))
                .thenApply(Cast::getId)
                .thenApply(CastId::new);
    }

    private CompletableFuture<CastCat> findOne(CastCatEntity entity) {
        return CompletableFuture
                .supplyAsync(() ->
                        new CastCat()
                                .setId(entity.getCastCatId().intValue())
                                .setDeletedFlag(false))
                .thenApply(CastCat.class::cast)
                .thenApply(Example::of)
                .thenApply(this.castCatSource::findOne)
                .thenApply(opt -> opt.orElseThrow(() -> new RuntimeException("キャストキャットが存在しません")));
    }

    private Cast toDto(CastEntity entity) {
        var castId = Optional
                .ofNullable(entity.getCastId())
                .map(CastId::intValue)
                .orElse(null);
        var storeId = Optional
                .of(entity.getStoreId())
                .map(StoreId::intValue)
                .get();
        var castCatId = Optional
                .of(entity.getCastCatEntity())
                .map(CastCatEntity::getCastCatId)
                .map(CastCatId::intValue)
                .get();
        return new Cast()
                .setId(castId)
                .setStoreId(storeId)
                .setCastCatId(castCatId)
                .setFirstAttendanceDate(null)
                .setLastAttendanceDate(null)
                .setEmploymentStatus(Cast.EmploymentStatus.main)
                .setMemo(entity.getMemo());
    }

    @Override
    @Async
    public CompletableFuture<CastCatId> resister(CastCatEntity entity, LocalDateTime receptionTime) {
        return CompletableFuture
                .supplyAsync(() -> entity)
                .thenApply(this::toDto)
                .thenApply(dto -> dto.setCreatedBy(0))
                .thenApply(CastCat.class::cast)
                .thenApply(dto -> this.castCatSource.insert(dto, receptionTime))
                .thenApply(CastCat::getId)
                .thenApply(CastCatId::new);
    }

    private CastCat toDto(CastCatEntity entity) {
        var castCatId = Optional
                .ofNullable(entity.getCastCatId())
                .map(CastCatId::intValue)
                .orElse(null);
        return new CastCat()
                .setId(castCatId)
                .setName(entity.getName())
                .setImage(entity.getImage())
                .setType(entity.getType())
                .setSex(CastCat.Sex.male)
                .setBirthdayDate(null)
                .setMemo(entity.getMemo());
    }

}
