package mabubu0203.com.github.catcafe.infra.source.r2dbc.dto.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.source.r2dbc.dto.BaseView;
import mabubu0203.com.github.catcafe.infra.source.r2dbc.dto.table.Cast;
import mabubu0203.com.github.catcafe.infra.source.r2dbc.dto.table.CastCat;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Table(value = "cast_view")
public class CastView extends BaseView {

  @Id
  @Column(value = "id")
  private String id;

  @Column(value = "cast_id")
  private Integer castId;

  @Column(value = "store_id")
  private Integer storeId;

  @Column(value = "employment_status")
  private Cast.EmploymentStatus employmentStatus;

  @Column(value = "first_attendance_date")
  private LocalDate firstAttendanceDate;

  @Column(value = "last_attendance_date")
  private LocalDate lastAttendanceDate;

  @Column(value = "cast_memo")
  private String castMemo;

  @Column(value = "cast_created_date_time")
  private LocalDateTime castCreatedDateTime;

  @Column(value = "cast_version")
  private Integer castVersion;

  @Column(value = "cast_updated_date_tim")
  private LocalDateTime castUpdatedDateTime;

  @Column(value = "cast_cat_id")
  private Integer castCatId;

  @Column(value = "cast_cat_name")
  private String castCatName;

  @Column(value = "cast_cat_image_url")
  private String castCatImage;

  @Column(value = "cast_cat_type")
  private String castCatType;

  @Column(value = "cast_cat_sex")
  private CastCat.Sex castCatSex;

  @Column(value = "cast_cat_memo")
  private String castCatMemo;

  @Column(value = "cast_cat_created_date_time")
  private LocalDateTime castCatCreatedDateTime;

  @Column(value = "cast_cat_version")
  private Integer castCatVersion;

  @Column(value = "cast_cat_updated_date_tim")
  private LocalDateTime castCatUpdatedDateTime;

}
