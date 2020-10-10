package mabubu0203.com.github.catcafe.api.service.impl.cast.converter;

import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastSearchConditions;

import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return new CastSearchServiceOutput()
                .setCasts(stream
                        .map(castEntity -> {
                            var castCatEntity = castEntity.getCastCatEntity();
                            return new CastSearchServiceOutput.CastObject()
                                    .setId(castEntity.getCastId().get().intValue())
                                    .setStoreId(castEntity.getStoreId().intValue())
                                    .setCastCat(
                                            new CastSearchServiceOutput.CastCatObject()
                                                    .setId(castCatEntity.getCastCatId().get().intValue())
                                                    .setName(castCatEntity.getName()));
                        })
                        .collect(Collectors.toList()));
    }
}
