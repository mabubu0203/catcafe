package mabubu0203.com.github.catcafe.api.controller.cast.mapper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.cast.model.input.CastCatResisterServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.CreateRequestMapper;
import org.openapitools.model.CastCatCreate;

@RequiredArgsConstructor
public class CastCatCreateRequestMapper implements CreateRequestMapper<CastCatCreate, CastCatResisterServiceInput> {

    private final String cats;

    @Override
    public CastCatResisterServiceInput apply(CastCatCreate castCatCreate) {
        return new CastCatResisterServiceInput()
                .setName(castCatCreate.getName())
                .setBrother(castCatCreate.getBrother())
                .setSister(castCatCreate.getSister());
    }

}
