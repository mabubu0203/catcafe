package mabubu0203.com.github.catcafe.infra.source.r2dbc.dto.table;

import com.fasterxml.jackson.annotation.JsonRawValue;
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
@Table(value = "cast_cat")
public class CastCat extends BaseTable<Integer> {

  @Id
  @Column(value = "id")
  private Integer id;

  @Column(value = "name")
  private String name;

  @Column(value = "image_url")
  private String image;

  @Column(value = "type")
  private String type;

  @Column(value = "sex")
  private Sex sex = Sex.male;

  @JsonRawValue
  @Column(value = "brothers")
  private String brothers;

  @JsonRawValue
  @Column(value = "sisters")
  private String sisters;

  @Column(value = "birthday_date")
  private LocalDate birthdayDate;

  @Column(value = "memo")
  private String memo;

  @Override
  @Transient
  public boolean isNew() {
    return super.isNew() || Objects.isNull(id);
  }

  @Getter
  public enum Sex {
    male,
    female
  }

}
