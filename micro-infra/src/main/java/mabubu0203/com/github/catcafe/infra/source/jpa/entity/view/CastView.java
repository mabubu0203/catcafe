package mabubu0203.com.github.catcafe.infra.source.jpa.entity.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.source.jpa.entity.BaseView;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Cast;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.CastCat;

import javax.persistence.*;

@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "cast_view")
public class CastView extends BaseView {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private String id;

    @Column(name = "cast_id", insertable = false, updatable = false)
    private Integer castId;

    @Column(name = "store_id", insertable = false, updatable = false)
    private Integer storeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "employment_status", insertable = false, updatable = false)
    private Cast.EmploymentStatus employmentStatus;

    @Column(name = "cast_cat_id", insertable = false, updatable = false)
    private Integer castCatId;

    @Column(name = "cast_cat_name", insertable = false, updatable = false)
    private String castCatName;

    @Column(name = "cast_cat_image_url", insertable = false, updatable = false)
    private String castCatImage;

    @Enumerated(EnumType.STRING)
    @Column(name = "cast_cat_sex", insertable = false, updatable = false)
    private CastCat.Sex castCatSex;

}
