package mabubu0203.com.github.catcafe.api.controller.store.service.model.input;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;

@Builder
@Getter
public class StoreModifyServiceInput implements ServiceInput {

  private final String cats;
  private final Integer storeId;
  private final String name;
  private final ContactObject contact;
  private final AddressObject address;
  private final LocalDate openDate;
  private final LocalDate closeDate;
  private final HoursObject hours;
  private final String memo;
  private final Integer version;

  @Builder
  @Getter
  public static class ContactObject {

    private final String phoneNumber;
    private final String mailAddress;

  }

  @Builder
  @Getter
  public static class AddressObject {

    private final String postalCode;
    private final Integer prefectureCode;
    private final String address1;
    private final String address2;
    private final String address3;
    private final String streetAddress;
    private final String buildingName;
    private final String supplement;

  }

  @Builder
  @Getter
  public static class HoursObject {

    private final LocalTime openingTime;
    private final LocalTime closingTime;
    private final String supplement;

  }

}
