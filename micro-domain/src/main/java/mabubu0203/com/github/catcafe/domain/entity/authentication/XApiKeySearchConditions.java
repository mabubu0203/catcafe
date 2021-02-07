package mabubu0203.com.github.catcafe.domain.entity.authentication;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.entity.SearchConditions;
import mabubu0203.com.github.catcafe.domain.value.XApiKeyToken;

@Accessors(fluent = true)
@Getter
@Setter
public class XApiKeySearchConditions extends SearchConditions {

  private XApiKeyToken token;
  private LocalDateTime specified_date_time;

  public XApiKeySearchConditions() {
    super();
  }

}
