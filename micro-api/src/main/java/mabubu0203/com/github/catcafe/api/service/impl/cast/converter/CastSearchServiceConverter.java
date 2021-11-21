package mabubu0203.com.github.catcafe.api.service.impl.cast.converter;

import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastSearchServiceOutput.CastObject;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastSearchServiceOutput.CastSearchServiceOutputBuilder;
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
        .map(casts -> CastSearchServiceOutput.builder().casts(casts))
        .map(CastSearchServiceOutputBuilder::build);
  }

  private CastObject toCastObject(CastEntity castEntity) {
    var castCatEntity = castEntity.getCastCatEntity();
    return CastSearchServiceOutput.CastObject.builder()
        .id(castEntity.getCastIdValue())
        .storeId(castEntity.getStoreIdValue())
        .common(
            CastSearchServiceOutput.CommonObject.builder()
                .createdDateTime(castEntity.getCreatedDateTime())
                .version(castEntity.getVersion())
                .updatedDateTime(castEntity.getUpdatedDateTime())
                .build())
        .castCat(
            CastSearchServiceOutput.CastCatObject.builder()
                .id(castCatEntity.getCastCatIdValue())
                .name(castCatEntity.getName())
                .common(
                    CastSearchServiceOutput.CommonObject.builder()
                        .createdDateTime(castCatEntity.getCreatedDateTime())
                        .version(castCatEntity.getVersion())
                        .updatedDateTime(castCatEntity.getUpdatedDateTime())
                        .build())
                .build())
        .build();
  }

}
