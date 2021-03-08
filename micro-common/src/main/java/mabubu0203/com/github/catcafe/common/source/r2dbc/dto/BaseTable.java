package mabubu0203.com.github.catcafe.common.source.r2dbc.dto;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;

@Accessors(chain = true)
@Data
public abstract class BaseTable<ID> implements Persistable<ID> {

  @CreatedDate
  @Column(value = "created_date_time")
  private LocalDateTime createdDateTime;

  @Column(value = "created_by")
  private Integer createdBy;

  @Version
  @Column(value = "version")
  private Integer version;

  @LastModifiedDate
  @Column(value = "updated_date_time")
  private LocalDateTime updatedDateTime;

  @Column(value = "updated_by")
  private Integer updatedBy;

  @Column(value = "deleted_date_time")
  private LocalDateTime deletedDateTime;

  @Column(value = "deleted_flag")
  private DeletedFlag deletedFlag = DeletedFlag.is_false;
  @Transient
  private boolean isNew;

  @Transient
  public boolean isExists() {
    return DeletedFlag.is_false.equals(this.deletedFlag);
  }

  @Transient
  public boolean isDeleted() {
    return !DeletedFlag.is_false.equals(this.deletedFlag);
  }

  @Getter
  public enum DeletedFlag {
    is_true,
    is_false
  }

}
