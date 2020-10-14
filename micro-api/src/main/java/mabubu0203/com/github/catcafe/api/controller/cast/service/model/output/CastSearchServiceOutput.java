package mabubu0203.com.github.catcafe.api.controller.cast.service.model.output;

import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.common.service.model.ServiceOutput;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class CastSearchServiceOutput implements ServiceOutput {

    private final List<CastObject> casts;

    @Builder
    @Getter
    public static class CastObject {
        private final Integer id;
        private final Integer storeId;
        private final CommonObject common;
        private final CastCatObject castCat;
    }

    @Builder
    @Getter
    public static class CastCatObject {
        private final Integer id;
        private final String name;
        private final CommonObject common;
    }

    @Builder
    @Getter
    public static class CommonObject {
        private final LocalDateTime createdDateTime;
        private final Integer version;
        private final LocalDateTime updatedDateTime;
    }

}
