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
@Table(name = "x_api_key")
@Where(clause = "deleted_flag = false")
public class XApiKey extends BaseTable {

    @Id
    @TableGenerator(
            name = "SeqGenerator",
            table = "sequence_generator",
            pkColumnName = "name",
            pkColumnValue = "x_api_key.id",
            valueColumnName = "value"
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SeqGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "client_ip")
    private String clientIp;

    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;

    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;
}
