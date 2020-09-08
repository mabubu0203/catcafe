package mabubu0203.com.github.catcafe.api.service.cast.model.input;

import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;

import java.util.List;
import java.util.Optional;

@Builder
@Getter
public class CastSearchServiceInput implements ServiceInput {

    private final String cats;
    private final Optional<List<Integer>> storeIds;
    private final Optional<List<Integer>> castIds;
    private final Optional<Integer> size;

}
