package mabubu0203.com.github.catcafe.api.controller.cast.service.model.input;

import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;

import java.time.LocalDate;

@Builder
@Getter
public class CastRegisterServiceInput implements ServiceInput {

    private final String cats;
    private final Integer castCatId;
    private final Integer storeId;
    private final LocalDate firstAttendanceDate;
    private final LocalDate lastAttendanceDate;
    private final String memo;

}
