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
  public CastCatResisterServiceInput apply(CastCatCreateRequest request) {
    return CastCatResisterServiceInput.builder()
        .cats(this.cats)
        .name(request.getName())
        .image(request.getImage())
        .type(request.getType())
        .sex(request.getSex().getValue())
        .birthdayDate(request.getBirthdayDate())
        .favorite(request.getFavorite())
        .dislike(request.getDislike())
        .prohibition(request.getProhibition())
        .brothers(request.getBrothers())
        .sisters(request.getSisters())
        .memo(request.getMemo())
        .build();
  }

}
