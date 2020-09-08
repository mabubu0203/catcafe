package mabubu0203.com.github.catcafe.domain.entity.cast;

import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.domain.value.StoreId;
import mabubu0203.com.github.catcafe.domain.value.CastId;

import java.util.Optional;

@Builder
@Getter
public class CastEntity {

    private Optional<CastId> castId;
    private StoreId storeId;
    private CastCatEntity CastCatEntity;

}
