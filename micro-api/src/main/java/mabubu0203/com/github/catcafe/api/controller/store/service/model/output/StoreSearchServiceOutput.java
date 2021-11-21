package mabubu0203.com.github.catcafe.api.controller.store.service.model.output;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.common.service.model.ServiceOutput;

@Builder
@Getter
public class StoreSearchServiceOutput implements ServiceOutput {

  private final List<StoreObject> stores;

  @Builder
  @Getter
  public static class StoreObject {

    private final Integer id;
    private final String name;
    private final ContactObject contact;
    private final AddressObject address;
    private final String memo;
    private final CommonObject common;
  }

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
    private final String prefectureCode;
    private final String address1;
    private final String address2;
    private final String address3;
    private final String streetAddress;
    private final String buildingName;
    private final String supplement;

  }

  @Builder
  @Getter
  public static class CommonObject {

    private final LocalDateTime createdDateTime;
    private final Integer version;
    private final LocalDateTime updatedDateTime;
  }

}
