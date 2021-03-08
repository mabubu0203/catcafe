package mabubu0203.com.github.catcafe.infra.repository.impl.cast;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.common.source.r2dbc.dto.BaseTable;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastCatEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastSearchConditions;
import mabubu0203.com.github.catcafe.domain.repository.cast.CastRepository;
import mabubu0203.com.github.catcafe.domain.value.CastCatId;
import mabubu0203.com.github.catcafe.domain.value.CastId;
import mabubu0203.com.github.catcafe.domain.value.StoreId;
import mabubu0203.com.github.catcafe.infra.source.r2dbc.CastCatSource;
import mabubu0203.com.github.catcafe.infra.source.r2dbc.CastSource;
import mabubu0203.com.github.catcafe.infra.source.r2dbc.CastViewSource;
import mabubu0203.com.github.catcafe.infra.source.r2dbc.dto.table.Cast;
import mabubu0203.com.github.catcafe.infra.source.r2dbc.dto.table.CastCat;
import mabubu0203.com.github.catcafe.infra.source.r2dbc.dto.view.CastView;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class CastRepositoryImpl implements CastRepository {

  private final CastSource castSource;
  private final CastCatSource castCatSource;
  private final CastViewSource castViewSource;

  @Override
  public Flux<CastEntity> search(CastSearchConditions searchConditions) {
    Predicate<CastView> storeIdInclude = castView -> {
      var storeIds = searchConditions.optStoreIds().orElseGet(Collections::emptyList);
      return storeIds.size() == 0 || storeIds.contains(castView.getStoreId());
    };
    Predicate<CastView> castIdInclude = castView -> {
      var castIds = searchConditions.optCastIds().orElseGet(Collections::emptyList);
      return castIds.size() == 0 || castIds.contains(castView.getCastId());
    };
    return this.castViewSource.findAll()

        .filter(storeIdInclude)
        .filter(castIdInclude)
        .map(this::convertCastEntity);
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
  public Mono<CastId> resister(CastEntity entity, LocalDateTime receptionTime) {
    return this.findOne(entity.getCastCatEntity())
        .map(dto -> entity)
        .map(this::toDto)
        .map(dto -> dto.setCreatedBy(0))
        .map(Cast.class::cast)
        .flatMap(dto -> this.castSource.insert(dto, receptionTime))
        .map(Cast::getId)
        .map(CastId::new);
  }

  private Mono<CastCat> findOne(CastCatEntity entity) {
    return Optional.of(new CastCat())
//        .map(dto -> dto.setVersion(entity.getVersion()))
//        .map(dto -> dto.setDeletedFlag(DeletedFlag.is_false))
//        .map(Store.class::cast)
        .map(dto -> dto.setId(entity.getCastCatId().intValue()))
        .map(CastCat::getId)
        .map(this.castCatSource::findById)
        .orElseThrow(RuntimeException::new)
        .filter(BaseTable::isExists)
        // 404で返却するためのエラーを検討
        .switchIfEmpty(Mono.error(new RuntimeException("キャスト(猫)が存在しません")));
  }

  private Cast toDto(CastEntity entity) {
    var castId = Optional.ofNullable(entity.getCastId())
        .map(CastId::intValue)
        .orElse(null);
    var storeId = Optional.of(entity.getStoreId())
        .map(StoreId::intValue)
        .get();
    var castCatId = Optional.of(entity.getCastCatEntity())
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
  public Mono<CastCatId> resister(CastCatEntity entity, LocalDateTime receptionTime) {
    return Optional.of(entity)
        .map(this::toDto)
        .map(dto -> dto.setCreatedBy(0))
        .map(CastCat.class::cast)
        .map(dto -> this.castCatSource.insert(dto, receptionTime))
        .orElseThrow(RuntimeException::new)
        .map(CastCat::getId)
        .map(CastCatId::new);
  }

  private CastCat toDto(CastCatEntity entity) {
    var castCatId = Optional.ofNullable(entity.getCastCatId())
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
