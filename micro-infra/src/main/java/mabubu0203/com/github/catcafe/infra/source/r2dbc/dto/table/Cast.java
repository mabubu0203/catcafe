package mabubu0203.com.github.catcafe.infra.source.r2dbc.dto.table;

import java.time.LocalDate;
import java.util.Objects;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.source.r2dbc.dto.BaseTable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Table(value = "cast")
public class Cast extends BaseTable<Integer> {

  @Id
  @Column(value = "id")
  private Integer id;

  @Column(value = "store_id")
  private Integer storeId;

  @Column(value = "cast_cat_id")
  private Integer castCatId;

  @Column(value = "first_attendance_date")
  private LocalDate firstAttendanceDate;

  @Column(value = "last_attendance_date")
  private LocalDate lastAttendanceDate;

  @Column(value = "employment_status")
  private EmploymentStatus employmentStatus = EmploymentStatus.main;

  @Column(value = "memo")
  private String memo;

  @Override
  @Transient
  public boolean isNew() {
    return super.isNew() || Objects.isNull(id);
  }

  @Getter
  public enum EmploymentStatus {
    main,
    sub
  }

}
