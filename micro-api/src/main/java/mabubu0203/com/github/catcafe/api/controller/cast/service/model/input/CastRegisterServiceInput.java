package mabubu0203.com.github.catcafe.api.controller.cast.service.model.input;

import lombok.Data;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;

import java.time.LocalDate;

@Accessors(chain = true)
@Data
public class CastRegisterServiceInput implements ServiceInput {

    private Integer castCatId;
    private Integer storeId;
    private LocalDate firstAttendanceDate;
    private LocalDate lastAttendanceDate;
    private String memo;

}
