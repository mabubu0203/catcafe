package mabubu0203.com.github.catcafe.api.controller.store.service.model.output;

import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.common.service.model.ServiceOutput;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class StoreSearchServiceOutput implements ServiceOutput {

    private final List<StoreObject> stores;

    @Builder
    @Getter
    public static class StoreObject {
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
