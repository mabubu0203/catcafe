package mabubu0203.com.github.catcafe.api.controller.cast.helper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastCatModifyServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.UpdateRequestMapper;
import org.openapitools.model.CastCatUpdateRequest;

@RequiredArgsConstructor
public class CastCatUpdateRequestMapper implements
    UpdateRequestMapper<CastCatUpdateRequest, CastCatModifyServiceInput> {

  private final String cats;
  private final Integer castCatId;

  @Override
  public CastCatModifyServiceInput apply(CastCatUpdateRequest request) {
    return CastCatModifyServiceInput.builder()
        .cats(this.cats)
        .castCatId(this.castCatId)
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
        .version(request.getVersion())
        .build();
  }

}
