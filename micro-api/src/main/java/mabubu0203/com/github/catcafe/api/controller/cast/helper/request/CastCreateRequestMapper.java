package mabubu0203.com.github.catcafe.api.controller.cast.helper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastRegisterServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.CreateRequestMapper;
import org.openapitools.model.CastCreateRequest;

@RequiredArgsConstructor
public class CastCreateRequestMapper implements
    CreateRequestMapper<CastCreateRequest, CastRegisterServiceInput> {

  private final String cats;
  private final Integer storeId;

  @Override
  public CastRegisterServiceInput apply(CastCreateRequest request) {
    return CastRegisterServiceInput.builder()
        .cats(this.cats)
        .storeId(this.storeId)
        .castCatId(request.getCastCatId())
        .firstAttendanceDate(request.getFirstAttendanceDate())
        .lastAttendanceDate(request.getLastAttendanceDate())
        .memo(request.getMemo())
        .build();
  }

}
