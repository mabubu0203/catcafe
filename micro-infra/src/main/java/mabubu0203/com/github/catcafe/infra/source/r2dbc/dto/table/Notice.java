package mabubu0203.com.github.catcafe.infra.source.r2dbc.dto.table;

import java.time.LocalDateTime;
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
@Table(value = "notice")
public class Notice extends BaseTable<Integer> {

  @Id
  @Column(value = "id")
  private Integer id;

  @Column(value = "store_id")
  private Integer storeId;

  @Column(value = "summary")
  private String summary;

  @Column(value = "detail")
  private String detail;

  @Column(value = "publication_start_date_time")
  private LocalDateTime publicationStartDateTime;

  @Column(value = "publication_end_date_time")
  private LocalDateTime publicationEndDateTime;

  @Override
  @Transient
  public boolean isNew() {
    return super.isNew() || Objects.isNull(id);
  }

}
