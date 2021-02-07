package mabubu0203.com.github.catcafe.api.service.impl.cast.converter;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastSearchConditions;

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

  public CastSearchServiceOutput toServiceOutput(Stream<CastEntity> stream) {
    return CastSearchServiceOutput.builder()
        .casts(stream
            .map(castEntity -> {
              var castCatEntity = castEntity.getCastCatEntity();
              return CastSearchServiceOutput.CastObject.builder()
                  .id(castEntity.getCastId().intValue())
                  .storeId(castEntity.getStoreId().intValue())
                  .common(CastSearchServiceOutput.CommonObject.builder()
                      .createdDateTime(castEntity.getCreatedDateTime())
                      .version(castEntity.getVersion())
                      .updatedDateTime(castEntity.getUpdatedDateTime())
                      .build())
                  .castCat(
                      CastSearchServiceOutput.CastCatObject.builder()
                          .id(castCatEntity.getCastCatId().intValue())
                          .name(castCatEntity.getName())
                          .common(CastSearchServiceOutput.CommonObject.builder()
                              .createdDateTime(castCatEntity.getCreatedDateTime())
                              .version(castCatEntity.getVersion())
                              .updatedDateTime(castCatEntity.getUpdatedDateTime())
                              .build())
                          .build())
                  .build();
            })
            .collect(Collectors.toList()))
        .build();
  }
}
