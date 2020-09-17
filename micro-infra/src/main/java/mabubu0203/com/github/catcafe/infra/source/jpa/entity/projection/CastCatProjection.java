package mabubu0203.com.github.catcafe.infra.source.jpa.entity.projection;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;

@AllArgsConstructor
@Data
public class CastCatProjection {
    @Column(name = "cast_id")
    private Integer castId;
    @Column(name = "store_id")
    private Integer storeId;
    @Column(name = "cast_cat_id")
    private Integer castCatId;
    @Column(name = "cast_cat_name")
    private String castCatName;
}
