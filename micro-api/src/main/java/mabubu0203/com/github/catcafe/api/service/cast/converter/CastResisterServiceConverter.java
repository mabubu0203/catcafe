package mabubu0203.com.github.catcafe.api.service.cast.converter;

import mabubu0203.com.github.catcafe.api.service.cast.model.input.CastRegisterServiceInput;
import mabubu0203.com.github.catcafe.api.service.cast.model.output.CastRegisterServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastCatEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastEntity;
import mabubu0203.com.github.catcafe.domain.value.CastCatId;
import mabubu0203.com.github.catcafe.domain.value.CastId;
import mabubu0203.com.github.catcafe.domain.value.StoreId;

import java.util.Optional;

public class CastResisterServiceConverter {

    public CastEntity fromInput(CastRegisterServiceInput input) {
        var castCatEntity = CastCatEntity.builder()
                .castCatId(Optional.of(new CastCatId(input.getCastCatId())))
                .build();
        return CastEntity.builder()
                .storeId(new StoreId(input.getStoreId()))
                .CastCatEntity(castCatEntity)
                .build();
    }

    public CastRegisterServiceOutput toOutput(CastId castId) {
        return new CastRegisterServiceOutput().setId(castId.intValue());
    }

}
