package mabubu0203.com.github.catcafe.api.service.cast.model.input;

import lombok.Data;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;

@Accessors(chain = true)
@Data
public class CastCatResisterServiceInput implements ServiceInput {

    private String name;

}
