package mabubu0203.com.github.catcafe.api.service.cast.model.input;

import lombok.Data;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;

import java.util.List;

@Accessors(chain = true)
@Data
public class CastSearchServiceInput implements ServiceInput {

    private List<Integer> storeIds;
    private Integer size;

}
