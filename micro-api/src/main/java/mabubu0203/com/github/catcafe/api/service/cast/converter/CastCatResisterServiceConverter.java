package mabubu0203.com.github.catcafe.api.service.cast.converter;

import mabubu0203.com.github.catcafe.api.service.cast.model.input.CastCatResisterServiceInput;
import mabubu0203.com.github.catcafe.api.service.cast.model.output.CastCatResisterServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastCatEntity;
import mabubu0203.com.github.catcafe.domain.value.CastCatId;

public class CastCatResisterServiceConverter {

    public CastCatEntity fromInput(CastCatResisterServiceInput input) {
        return CastCatEntity.builder()
                .name(input.getName())
                .build();
    }

    public CastCatResisterServiceOutput toOutput(CastCatId castCatId) {
        return new CastCatResisterServiceOutput().setId(castCatId.intValue());
    }

}
