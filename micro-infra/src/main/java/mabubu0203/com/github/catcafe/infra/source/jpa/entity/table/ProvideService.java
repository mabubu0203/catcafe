package mabubu0203.com.github.catcafe.infra.source.jpa.entity.table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.source.jpa.entity.BaseTable;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "provide_service")
@Where(clause = "deleted_flag = false")
public class ProvideService extends BaseTable {

    @Id
    @TableGenerator(
            name = "SeqGenerator",
            table = "sequence_generator",
            pkColumnName = "name",
            pkColumnValue = "provide_service.id",
            valueColumnName = "value"
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "SeqGenerator"
    )
    @Column(name = "id")
    private Integer id;

    @Column(
            name = "store_id",
            nullable = false
    )
    private Integer storeId;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "price",
            nullable = false,
            precision = 10,
            scale = 3
    )
    private BigDecimal price;

    @Column(name = "detail")
    private String detail;

}
