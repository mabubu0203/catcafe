package mabubu0203.com.github.catcafe.domain.value;

import java.util.Optional;

public record NoticeId(Integer value) {

  public static NoticeId emptyId() {
    return new NoticeId(null);
  }

  public boolean isEmpty() {
    return Optional.of(value).isEmpty();
  }

}
