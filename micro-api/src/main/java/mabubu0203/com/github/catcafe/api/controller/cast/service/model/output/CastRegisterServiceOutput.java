package mabubu0203.com.github.catcafe.api.controller.cast.service.model.output;

import lombok.Data;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.service.model.ServiceOutput;

@Accessors(chain = true)
@Data
public class CastRegisterServiceOutput implements ServiceOutput {

    private Integer id;

}