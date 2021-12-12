package mabubu0203.com.github.catcafe.api.controller.cast.service.model.output;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.common.service.model.ServiceOutput;

@Builder
@Getter
public class CastSearchServiceOutput implements ServiceOutput {

  private final List<CastObject> casts;

  @Builder
  @Getter
  public static class CastObject {

    private final Integer id;
    private final Integer storeId;
    private final LocalDate firstAttendanceDate;
    private final LocalDate lastAttendanceDate;
    private final String memo;
    private final CommonObject common;
    private final CastCatObject castCat;
  }

  @Builder
  @Getter
  public static class CastCatObject {

    private final Integer id;
    private final String name;
    private final String image;
    private final String type;
    private final LocalDate birthdayDate;
    private final String memo;
    private final CommonObject common;
  }

  @Builder
  @Getter
  public static class CommonObject {

    private final LocalDateTime createdDateTime;
    private final Integer version;
    private final LocalDateTime updatedDateTime;
  }

}
