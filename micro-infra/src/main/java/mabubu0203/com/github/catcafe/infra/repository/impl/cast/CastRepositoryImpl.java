package mabubu0203.com.github.catcafe.infra.repository.impl.cast;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.common.exception.ResourceNotFoundException;
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

  @Override
  public Mono<CastId> resister(CastEntity entity, LocalDateTime receptionTime) {
    return this.findDto(entity.getCastCatEntity().getCastCatId())
        .map(dto -> entity)
        .map(this::attach)
        .map(dto -> dto.setCreatedBy(0))
        .map(Cast.class::cast)
        .flatMap(dto -> this.castSource.insert(dto, receptionTime))
        .mapNotNull(Cast::getId)
        .map(CastId::new);
  }

  @Override
  public Mono<CastCatId> resister(CastCatEntity entity, LocalDateTime receptionTime) {
    return Optional.of(entity)
        .map(this::attach)
        .map(dto -> dto.setCreatedBy(0))
        .map(CastCat.class::cast)
        .map(dto -> this.castCatSource.insert(dto, receptionTime))
        .orElseThrow(RuntimeException::new)
        .mapNotNull(CastCat::getId)
        .map(CastCatId::new);
  }

  private CastEntity convertCastEntity(CastView dto) {
    var castCatId = new CastCatId(dto.getCastCatId());
    var castCatEntity = CastCatEntity.builder()
        .castCatId(castCatId)
        .name(dto.getCastCatName())
        .image(dto.getCastCatImage())
        .sex(dto.getCastCatSex().name())
        .createdDateTime(dto.getCastCatCreatedDateTime())
        .version(dto.getCastCatVersion())
        .updatedDateTime(dto.getCastCatUpdatedDateTime())
        .build();

    var castId = new CastId(dto.getCastId());
    var storeId = new StoreId(dto.getStoreId());

    return CastEntity.builder()
        .castId(castId)
        .storeId(storeId)
        .createdDateTime(dto.getCastCreatedDateTime())
        .version(dto.getCastVersion())
        .updatedDateTime(dto.getCastUpdatedDateTime())
        .castCatEntity(castCatEntity)
        .build();
  }

  private Mono<CastCat> findDto(CastCatId castCatId) {
    return this.castCatSource.findById(castCatId.value())
        .filter(BaseTable::isExists)
        // 404で返却するためのエラーを検討
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("キャスト(猫)が存在しません")));
  }

  private Cast attach(CastEntity entity) {
    return this.attach(null, entity);
  }

  private Cast attach(Cast dto, CastEntity entity) {
    return Optional.ofNullable(dto)
        .orElse(new Cast())
        .setId(entity.getCastIdValue())
        .setStoreId(entity.getStoreIdValue())
        .setCastCatId(entity.getCastCatIdValue())
        .setFirstAttendanceDate(null)
        .setLastAttendanceDate(null)
        .setEmploymentStatus(Cast.EmploymentStatus.main)
        .setMemo(entity.getMemo());
  }

  private CastCat attach(CastCatEntity entity) {
    return this.attach(null, entity);
  }

  private CastCat attach(CastCat dto, CastCatEntity entity) {
    return Optional.ofNullable(dto)
        .orElse(new CastCat())
        .setId(entity.getCastCatIdValue())
        .setName(entity.getName())
        .setImage(entity.getImage())
        .setType(entity.getType())
        .setSex(CastCat.Sex.male)
        .setBirthdayDate(null)
        .setMemo(entity.getMemo());
  }

}
