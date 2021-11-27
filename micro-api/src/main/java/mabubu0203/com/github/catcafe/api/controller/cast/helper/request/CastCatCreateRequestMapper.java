package mabubu0203.com.github.catcafe.api.controller.cast.helper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastCatResisterServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.CreateRequestMapper;
import org.openapitools.model.CastCatCreateRequest;

@RequiredArgsConstructor
public class CastCatCreateRequestMapper implements
    CreateRequestMapper<CastCatCreateRequest, CastCatResisterServiceInput> {

  private final String cats;

  @Override
  public CastCatResisterServiceInput apply(CastCatCreateRequest castCatCreate) {
    return CastCatResisterServiceInput.builder()
        .cats(this.cats)
        .name(castCatCreate.getName())
        .image(castCatCreate.getImage())
        .type(castCatCreate.getType())
        .brother(castCatCreate.getBrother())
        .sister(castCatCreate.getSister())
        .memo(castCatCreate.getMemo())
        .build();
  }

}
