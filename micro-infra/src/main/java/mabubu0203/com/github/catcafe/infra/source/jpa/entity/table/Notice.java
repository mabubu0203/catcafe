package mabubu0203.com.github.catcafe.infra.source.jpa.entity.table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.source.jpa.entity.BaseTable;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "notice")
@Where(clause = "deleted_flag = false")
public class Notice extends BaseTable {

    @Id
    @TableGenerator(
            name = "SeqGenerator",
            table = "sequence_generator",
            pkColumnName = "name",
            pkColumnValue = "notice.id",
            valueColumnName = "value"
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SeqGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "store_id")
    private Integer storeId;

    @Column(name = "summary")
    private String summary;

    @Column(name = "detail")
    private String detail;

    @Column(name = "publication_start_date_time")
    private LocalDateTime publicationStartDateTime;

    @Column(name = "publication_end_date_time")
    private LocalDateTime publicationEndDateTime;

}
