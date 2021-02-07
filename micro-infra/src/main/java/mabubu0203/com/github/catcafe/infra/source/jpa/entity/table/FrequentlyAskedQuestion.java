package mabubu0203.com.github.catcafe.infra.source.jpa.entity.table;

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
import mabubu0203.com.github.catcafe.common.source.jpa.entity.BaseTable;
import org.hibernate.annotations.Where;

@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "frequently_asked_question")
@Where(clause = "deleted_flag = false")
public class FrequentlyAskedQuestion extends BaseTable {

  @Id
  @TableGenerator(
      name = "SeqGenerator",
      table = "sequence_generator",
      pkColumnName = "name",
      pkColumnValue = "frequently_asked_question.id",
      valueColumnName = "value"
  )
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "SeqGenerator")
  @Column(name = "id")
  private Integer id;

  @Column(name = "store_id", nullable = false)
  private Integer storeId;

  @Column(name = "category", nullable = false)
  private String category;

  @Column(name = "question_summary", nullable = false)
  private String questionSummary;

  @Column(name = "answer_summary", nullable = false)
  private String answerSummary;

}
