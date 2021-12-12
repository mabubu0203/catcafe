package mabubu0203.com.github.catcafe.api.service.impl.cast.converter;

import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastCatSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastCatSearchServiceOutput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastCatEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastCatSearchConditions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CastCatSearchServiceConverter {

  public CastCatSearchConditions toSearchCondition(CastCatSearchServiceInput input) {
    return new CastCatSearchConditions(
        input.getOptPage().orElse(0),
        input.getOptSize().orElse(20),
        input.getOptSortKeys()
    )
        .optCastCatIds(input.getOptCastCatIds());
  }

  public Mono<CastCatSearchServiceOutput> toOutput(Flux<CastCatEntity> flux) {
    return flux.map(this::toCastCatObject)
        .collectList()
        .map(CastCatSearchServiceOutput.builder()::castCats)
        .map(CastCatSearchServiceOutput.CastCatSearchServiceOutputBuilder::build);
  }

  private CastCatSearchServiceOutput.CastCatObject toCastCatObject(CastCatEntity castCatEntity) {
    var common = CastSearchServiceOutput.CommonObject.builder()
        .createdDateTime(castCatEntity.getCreatedDateTime())
        .version(castCatEntity.getVersion())
        .updatedDateTime(castCatEntity.getUpdatedDateTime())
        .build();
    return CastCatSearchServiceOutput.CastCatObject.builder()
        .id(castCatEntity.getCastCatIdValue())
        .name(castCatEntity.getName())
        .image(castCatEntity.getImageValue())
        .type(castCatEntity.getType())
        .birthdayDate(castCatEntity.getBirthdayDate())
        .memo(castCatEntity.getMemoValue())
        .common(common)
        .build();
  }

}
