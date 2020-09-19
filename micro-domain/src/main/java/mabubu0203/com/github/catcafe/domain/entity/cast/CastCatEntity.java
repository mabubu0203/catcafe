package mabubu0203.com.github.catcafe.domain.entity.cast;

import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.domain.value.CastCatId;

import java.util.Optional;

@Builder
@Getter
public class CastCatEntity {

    private final Optional<CastCatId> castCatId;
    private final String name;
    private final String image;
    private final String sex;
    private final String memo;

}
