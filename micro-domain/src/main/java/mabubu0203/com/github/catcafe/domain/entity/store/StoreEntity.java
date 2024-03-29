package mabubu0203.com.github.catcafe.domain.entity.store;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.domain.value.MailAddress;
import mabubu0203.com.github.catcafe.domain.value.Memo;
import mabubu0203.com.github.catcafe.domain.value.PhoneNumber;
import mabubu0203.com.github.catcafe.domain.value.PostalCode;
import mabubu0203.com.github.catcafe.domain.value.Prefecture;
import mabubu0203.com.github.catcafe.domain.value.StoreId;
import mabubu0203.com.github.catcafe.domain.value.Supplement;

/**
 * 店舗
 */
@Builder
@Getter
public class StoreEntity {

  private final StoreId storeId;
  private final String name;
  private final PhoneNumber phoneNumber;
  private final MailAddress mailAddress;
  private final PostalCode postalCode;
  private final Prefecture prefecture;
  private final String address1;
  private final String address2;
  private final String address3;
  private final String streetAddress;
  private final String buildingName;
  private final Supplement addressSupplement;
  private final LocalDate openDate;
  private final LocalDate closeDate;
  private final LocalTime openingTime;
  private final LocalTime closingTime;
  private final Supplement hoursSupplement;
  private final Memo memo;
  private final LocalDateTime createdDateTime;
  private final Integer version;
  private final LocalDateTime updatedDateTime;

  public Integer getStoreIdValue() {
    return Optional.ofNullable(this.storeId)
        .map(StoreId::value)
        .orElse(null);
  }

  public String getPhoneNumberValue() {
    return Optional.ofNullable(this.phoneNumber)
        .map(PhoneNumber::value)
        .orElse(null);
  }

  public String getMailAddressValue() {
    return Optional.ofNullable(this.mailAddress)
        .map(MailAddress::value)
        .orElse(null);
  }

  public String getPostalCodeValue() {
    return Optional.ofNullable(this.postalCode)
        .map(PostalCode::value)
        .orElse(null);
  }

  public Integer getPrefectureCode() {
    return Optional.ofNullable(this.prefecture)
        .map(Prefecture::getCode)
        .orElse(null);
  }

  public String getPrefectureLabel() {
    return Optional.ofNullable(this.prefecture)
        .map(Prefecture::getLabel)
        .orElse(null);
  }

  public String getAddressSupplementValue() {
    return Optional.ofNullable(this.addressSupplement)
        .map(Supplement::value)
        .orElse(null);
  }

  public String getHoursSupplementValue() {
    return Optional.ofNullable(this.hoursSupplement)
        .map(Supplement::value)
        .orElse(null);
  }

  public String getMemoValue() {
    return Optional.ofNullable(this.memo)
        .map(Memo::value)
        .orElse(null);
  }

}
