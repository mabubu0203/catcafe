package mabubu0203.com.github.catcafe.common.source.redis.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.redis.core.TimeToLive;

@Accessors(chain = true)
@Data
public abstract class BaseHash {

  @TimeToLive
  private Long expiration = 86400L;

}
