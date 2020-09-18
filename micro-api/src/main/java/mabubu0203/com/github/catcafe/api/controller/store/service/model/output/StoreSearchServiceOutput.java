package mabubu0203.com.github.catcafe.api.controller.store.service.model.output;

import lombok.Data;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.service.model.ServiceOutput;

import java.util.List;

@Accessors(chain = true)
@Data
public class StoreSearchServiceOutput implements ServiceOutput {

    private List<StoreObject> stores;

    @Data
    public static class StoreObject {
        private Integer id;
        private String name;
    }

}
