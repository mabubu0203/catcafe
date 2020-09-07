package mabubu0203.com.github.catcafe.api.service.cast.model.input;

import lombok.Data;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;

@Accessors(chain = true)
@Data
public class CastRegisterServiceInput implements ServiceInput {

    private Integer storeId;
    private Integer castCatId;

}
