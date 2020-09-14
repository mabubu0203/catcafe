package mabubu0203.com.github.catcafe.domain.entity.store;

import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.domain.value.StoreId;

import java.util.Optional;

@Builder
@Getter
public class StoreEntity {

    private final Optional<StoreId> storeId;
    private final String name;

}
