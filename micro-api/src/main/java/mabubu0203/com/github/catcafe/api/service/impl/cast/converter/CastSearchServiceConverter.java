package mabubu0203.com.github.catcafe.api.service.impl.cast.converter;

import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastCatEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastSearchConditions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CastSearchServiceConverter {

  public CastSearchConditions toSearchCondition(CastSearchServiceInput input) {
    return new CastSearchConditions(
        input.getOptPage().orElse(0),
        input.getOptSize().orElse(20),
        input.getOptSortKeys()
    )
        .optStoreIds(input.getOptStoreIds())
        .optCastIds(input.getOptCastIds());
  }

  public Mono<CastSearchServiceOutput> toOutput(Flux<CastEntity> flux) {
    return flux.map(this::toCastObject)
        .collectList()
        .map(CastSearchServiceOutput.builder()::casts)
        .map(CastSearchServiceOutput.CastSearchServiceOutputBuilder::build);
  }

  private CastSearchServiceOutput.CastObject toCastObject(CastEntity castEntity) {
    var common = CastSearchServiceOutput.CommonObject.builder()
        .createdDateTime(castEntity.getCreatedDateTime())
        .version(castEntity.getVersion())
        .updatedDateTime(castEntity.getUpdatedDateTime())
        .build();
    var castCat = this.toCastCatObject(castEntity.getCastCatEntity());
    return CastSearchServiceOutput.CastObject.builder()
        .id(castEntity.getCastIdValue())
        .storeId(castEntity.getStoreIdValue())
        .employmentStatus(castEntity.getEmploymentStatusLabel())
        .firstAttendanceDate(castEntity.getFirstAttendanceDate())
        .lastAttendanceDate(castEntity.getLastAttendanceDate())
        .memo(castEntity.getMemoValue())
        .common(common)
        .castCat(castCat)
        .build();
  }

  private CastSearchServiceOutput.CastCatObject toCastCatObject(CastCatEntity castCatEntity) {
    var common = CastSearchServiceOutput.CommonObject.builder()
        .createdDateTime(castCatEntity.getCreatedDateTime())
        .version(castCatEntity.getVersion())
        .updatedDateTime(castCatEntity.getUpdatedDateTime())
        .build();
    return CastSearchServiceOutput.CastCatObject.builder()
        .id(castCatEntity.getCastCatIdValue())
        .name(castCatEntity.getName())
        .image(castCatEntity.getImageValue())
        .type(castCatEntity.getType())
        .memo(castCatEntity.getMemoValue())
        .common(common)
        .build();
  }

}
