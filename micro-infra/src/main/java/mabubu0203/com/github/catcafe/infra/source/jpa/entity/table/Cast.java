package mabubu0203.com.github.catcafe.infra.source.jpa.entity.table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.source.jpa.entity.BaseTable;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "cast")
@Where(clause = "deleted_flag = false")
public class Cast extends BaseTable {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "store_id")
    private Integer storeId;

    @Column(name = "cat_cast_id")
    private Integer catCastId;

    @Enumerated(EnumType.STRING)
    @Column(name = "employment_status")
    private EmploymentStatus employmentStatus = EmploymentStatus.main;

    @Getter
    enum EmploymentStatus {
        main,
        sub
    }

}
