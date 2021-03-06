package mabubu0203.com.github.catcafe.infra.source.jpa.dto.table;

import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.source.jpa.dto.BaseTable;
import org.hibernate.annotations.Where;

@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "store")
@Where(clause = "deleted_flag = false")
public class Store extends BaseTable {

  @Id
  @TableGenerator(
      name = "SeqGenerator",
      table = "sequence_generator",
      pkColumnName = "name",
      pkColumnValue = "store.id",
      valueColumnName = "value"
  )
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "SeqGenerator")
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "opening_time")
  private LocalTime openingTime;

  @Column(name = "closing_time")
  private LocalTime closingTime;

}
