package mabubu0203.com.github.catcafe.infra.source.jpa.entity.table;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.source.jpa.entity.BaseTable;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Column(name = "image")
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
