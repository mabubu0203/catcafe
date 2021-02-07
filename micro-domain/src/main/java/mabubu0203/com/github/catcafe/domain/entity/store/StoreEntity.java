package mabubu0203.com.github.catcafe.domain.entity.store;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.domain.value.StoreId;

@Builder
@Getter
public class StoreEntity {

  private final StoreId storeId;
  private final String name;
  private final LocalDate openDate;
  private final LocalDate closeDate;
  private final String phoneNumber;
  private final String mailAddress;
  private final String postalCode;
  private final String prefectures;
  private final LocalTime openingTime;
  private final LocalTime closingTime;
  private final String hoursAside;
  private final LocalDateTime createdDateTime;
  private final Integer version;
  private final LocalDateTime updatedDateTime;

}
