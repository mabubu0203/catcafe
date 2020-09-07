package mabubu0203.com.github.catcafe.api.service.cast.model.output;

import lombok.Data;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.service.model.ServiceOutput;

import java.util.List;

@Accessors(chain = true)
@Data
public class CastSearchServiceOutput implements ServiceOutput {

    private List<CastObject> casts;

    @Data
    public static class CastObject {
        private Integer id;
        private Integer storeId;
        private CastCatObject castCat;
    }

    @Data
    public static class CastCatObject {
        private Integer id;
        private String name;
    }

}
