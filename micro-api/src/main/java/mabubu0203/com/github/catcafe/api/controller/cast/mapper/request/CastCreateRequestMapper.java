package mabubu0203.com.github.catcafe.api.controller.cast.mapper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.cast.model.input.CastRegisterServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.CreateRequestMapper;
import org.openapitools.model.CastCreate;

@RequiredArgsConstructor
public class CastCreateRequestMapper implements CreateRequestMapper<CastCreate, CastRegisterServiceInput> {

    private final String cats;
    private final Integer storeId;

    @Override
    public CastRegisterServiceInput apply(CastCreate castCreate) {
        return new CastRegisterServiceInput()
                .setStoreId(this.storeId)
                .setCastCatId(castCreate.getCastCatId());
    }

}