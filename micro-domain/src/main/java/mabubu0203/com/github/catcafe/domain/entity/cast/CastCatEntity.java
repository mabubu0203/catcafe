package mabubu0203.com.github.catcafe.domain.entity.cast;

import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.domain.value.CastCatId;

import java.util.Optional;

@Builder
@Getter
public class CastCatEntity {

    private Optional<CastCatId> castCatId;
    private String name;

}
