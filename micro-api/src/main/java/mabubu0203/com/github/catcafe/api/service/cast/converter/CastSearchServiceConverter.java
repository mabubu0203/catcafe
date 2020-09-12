package mabubu0203.com.github.catcafe.api.service.cast.converter;

import mabubu0203.com.github.catcafe.api.service.cast.model.input.CastSearchServiceInput;
import mabubu0203.com.github.catcafe.api.service.cast.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastSearchConditions;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CastSearchServiceConverter {

    public CastSearchConditions toSearchCondition(CastSearchServiceInput castSearchServiceInput) {
        return new CastSearchConditions(
                castSearchServiceInput.getOptPage().orElse(0),
                castSearchServiceInput.getOptSize().orElse(20),
                castSearchServiceInput.getOptSortKeys()
        )
                .optStoreIds(castSearchServiceInput.getOptStoreIds())
                .optCastIds(castSearchServiceInput.getOptCastIds());
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
