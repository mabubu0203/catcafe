package mabubu0203.com.github.catcafe.infra.source.r2dbc.dto.table;

import java.math.BigDecimal;
import java.util.Objects;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Table(value = "provide_service")
public class ProvideService extends BaseTable<Integer> {

  @Id
  @Column(value = "id")
  private Integer id;

  @Column(value = "store_id")
  private Integer storeId;

  @Column(value = "name")
  private String name;

  @Column(value = "price")
  private BigDecimal price;

  @Column(value = "detail")
  private String detail;

  @Override
  @Transient
  public boolean isNew() {
    return super.isNew() || Objects.isNull(id);
  }

}
