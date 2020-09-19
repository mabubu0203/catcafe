package mabubu0203.com.github.catcafe.infra.source.jpa.entity.table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.source.jpa.entity.BaseTable;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "cast")
@Where(clause = "deleted_flag = false")
public class Cast extends BaseTable {

    @Id
    @TableGenerator(
            name = "SeqGenerator",
            table = "sequence_generator",
            pkColumnName = "name",
            pkColumnValue = "cast.id",
            valueColumnName = "value"
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SeqGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "store_id", nullable = false)
    private Integer storeId;

    @Column(name = "cast_cat_id", nullable = false)
    private Integer castCatId;

    @Column(name = "first_attendance_date")
    private LocalDate firstAttendanceDate;

    @Column(name = "last_attendance_date")
    private LocalDate lastAttendanceDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "employment_status", nullable = false)
    private EmploymentStatus employmentStatus = EmploymentStatus.main;

    @Column(name = "memo")
    private String memo;

    @Getter
    public enum EmploymentStatus {
        main,
        sub
    }

}
