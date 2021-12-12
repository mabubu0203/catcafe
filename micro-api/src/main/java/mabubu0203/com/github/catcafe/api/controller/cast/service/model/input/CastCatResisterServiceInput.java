package mabubu0203.com.github.catcafe.api.controller.cast.service.model.input;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;

@Builder
@Getter
public class CastCatResisterServiceInput implements ServiceInput {

  private final String cats;
  private final String name;
  private final String image;
  private final String type;
  private final LocalDate birthdayDate;
  private final String favorite;
  private final String dislike;
  private final String prohibition;
  private final List<Integer> brothers;
  private final List<Integer> sisters;
  private final String memo;

}
