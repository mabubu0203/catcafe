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
  // Supplementに変更
  private final String addressSupplement;
  private final LocalDate openDate;
  private final LocalDate closeDate;
  private final LocalTime openingTime;
  private final LocalTime closingTime;
  // Supplementに変更
  private final String hoursSupplement;
  private final Memo memo;
  private final LocalDateTime createdDateTime;
  private final Integer version;
  private final LocalDateTime updatedDateTime;

  public Integer getStoreIdValue() {
    return Optional.of(this.storeId)
        .map(StoreId::value)
        .orElse(null);
  }

  public String getPhoneNumberValue() {
    return Optional.of(this.phoneNumber)
        .map(PhoneNumber::value)
        .orElse(null);
  }

  public String getMailAddressValue() {
    return Optional.of(this.mailAddress)
        .map(MailAddress::value)
        .orElse(null);
  }

  public String getPostalCodeValue() {
    return Optional.of(this.postalCode)
        .map(PostalCode::value)
        .orElse(null);
  }

  public Integer getPrefectureCode() {
    return Optional.of(this.prefecture)
        .map(Prefecture::getCode)
        .orElse(null);
  }

  public String getPrefectureLabel() {
    return Optional.of(this.prefecture)
        .map(Prefecture::getLabel)
        .orElse(null);
  }

  public String getMemoValue() {
    return Optional.of(this.memo)
        .map(Memo::value)
        .orElse(null);
  }

}
