package mabubu0203.com.github.catcafe.common.source.jpa.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Optional;

@Accessors(chain = true)
@Data
@MappedSuperclass
public abstract class BaseTable {

    @CreatedDate
    @Column(
            name = "created_date_time",
            nullable = false
    )
    private LocalDateTime createdDateTime;

    @Column(
            name = "created_by",
            nullable = false
    )
    private Integer createdBy;

    @Version
    @Column(
            name = "version",
            nullable = false
    )
    private Integer version;

    @LastModifiedDate
    @Column(
            name = "updated_date_time"
    )
    private LocalDateTime updatedDateTime;

    @Column(
            name = "updated_by"
    )
    private Integer updatedBy;

    @Column(
            name = "deleted_date_time"
    )
    private LocalDateTime deletedDateTime;

    @Column(
            name = "deleted_flag",
            nullable = false
    )
    private Boolean deletedFlag = Boolean.FALSE;

    public Boolean isDeleted() {
        return Optional.of(this.deletedFlag).get();
    }

}
