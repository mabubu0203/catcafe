package mabubu0203.com.github.catcafe.infra.source.jpa.entity.table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.source.jpa.entity.BaseTable;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "cast_cat")
@Where(clause = "deleted_flag = false")
public class CastCat extends BaseTable {

    @Id
    @TableGenerator(
            name = "SeqGenerator",
            table = "sequence_generator",
            pkColumnName = "name",
            pkColumnValue = "cast_cat.id",
            valueColumnName = "value"
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SeqGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

}
