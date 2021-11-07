package mabubu0203.com.github.catcafe.domain.entity.store;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.domain.value.MailAddress;
import mabubu0203.com.github.catcafe.domain.value.Memo;
import mabubu0203.com.github.catcafe.domain.value.PhoneNumber;
import mabubu0203.com.github.catcafe.domain.value.PostalCode;
import mabubu0203.com.github.catcafe.domain.value.StoreId;

@Builder
@Getter
public class StoreEntity {

  private final StoreId storeId;
  private final String name;
  private final PhoneNumber phoneNumber;
  private final MailAddress mailAddress;
  private final PostalCode postalCode;
  private final String prefectureCode;
  private final String address1;
  private final String address2;
  private final String address3;
  private final String streetAddress;
  private final String buildingName;
  private final String addressSupplement;
  private final LocalDate openDate;
  private final LocalDate closeDate;
  private final LocalTime openingTime;
  private final LocalTime closingTime;
  private final String hoursSupplement;
  private final Memo memo;
  private final LocalDateTime createdDateTime;
  private final Integer version;
  private final LocalDateTime updatedDateTime;

}
