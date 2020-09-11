package mabubu0203.com.github.catcafe.domain.entity.cast;

import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.domain.value.CastId;
import mabubu0203.com.github.catcafe.domain.value.StoreId;

import java.util.Optional;

@Builder
@Getter
public class CastEntity {

    private final Optional<CastId> castId;
    private final StoreId storeId;
    private final CastCatEntity CastCatEntity;

}
