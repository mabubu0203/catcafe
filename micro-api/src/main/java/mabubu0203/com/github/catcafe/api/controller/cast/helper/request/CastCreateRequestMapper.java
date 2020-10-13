package mabubu0203.com.github.catcafe.api.controller.cast.helper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastRegisterServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.CreateRequestMapper;
import org.openapitools.model.CastCreate;

@RequiredArgsConstructor
public class CastCreateRequestMapper implements CreateRequestMapper<CastCreate, CastRegisterServiceInput> {

    private final String cats;
    private final Integer storeId;

    @Override
    public CastRegisterServiceInput apply(CastCreate castCreate) {
        return CastRegisterServiceInput.builder()
                .cats(this.cats)
                .storeId(this.storeId)
                .castCatId(castCreate.getCastCatId())
                .firstAttendanceDate(castCreate.getFirstAttendanceDate())
                .lastAttendanceDate(castCreate.getLastAttendanceDate())
                .memo(castCreate.getMemo())
                .build();
    }

}
