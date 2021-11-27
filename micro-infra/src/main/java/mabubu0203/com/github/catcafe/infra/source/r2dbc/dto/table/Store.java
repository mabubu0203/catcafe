package mabubu0203.com.github.catcafe.infra.source.r2dbc.dto.table;

import java.time.LocalDate;
import java.time.LocalTime;
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
@Table(value = "store")
public class Store extends BaseTable<Integer> {

  @Id
  @Column(value = "id")
  private Integer id;

  @Column(value = "name")
  private String name;

  @Column(value = "phone_number")
  private String phoneNumber;

  @Column(value = "mail_address")
  private String mailAddress;

  @Column(value = "postal_code")
  private String postalCode;

  @Column(value = "prefecture_code")
  private int prefectureCode;

  @Column(value = "address_1")
  private String address1;

  @Column(value = "address_2")
  private String address2;

  @Column(value = "address_3")
  private String address3;

  @Column(value = "street_address")
  private String streetAddress;

  @Column(value = "building_name")
  private String buildingName;

  @Column(value = "address_supplement")
  private String addressSupplement;

  @Column(value = "open_date")
  private LocalDate openDate;

  @Column(value = "close_date")
  private LocalDate closeDate;

  @Column(value = "opening_time")
  private LocalTime openingTime;

  @Column(value = "closing_time")
  private LocalTime closingTime;

  @Column(value = "hours_supplement")
  private String hoursSupplement;

  @Column(value = "memo")
  private String memo;

  @Override
  @Transient
  public boolean isNew() {
    return super.isNew() || Objects.isNull(id);
  }

}
