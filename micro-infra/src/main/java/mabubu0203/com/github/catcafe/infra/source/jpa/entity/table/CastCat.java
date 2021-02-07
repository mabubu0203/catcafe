package mabubu0203.com.github.catcafe.infra.source.jpa.entity.table;

import com.fasterxml.jackson.annotation.JsonRawValue;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.source.jpa.entity.BaseTable;
import org.hibernate.annotations.Where;

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

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "image_url")
  private String image;

  @Column(name = "type")
  private String type;

  @Enumerated(EnumType.STRING)
  @Column(name = "sex", nullable = false)
  private Sex sex = Sex.male;

  @JsonRawValue
  @Column(name = "brothers", columnDefinition = "json")
  private String brothers;

  @JsonRawValue
  @Column(name = "sisters", columnDefinition = "json")
  private String sisters;

  @Column(name = "birthday_date")
  private LocalDate birthdayDate;

  @Column(name = "memo")
  private String memo;

  @Getter
  public enum Sex {
    male,
    female
  }

}
