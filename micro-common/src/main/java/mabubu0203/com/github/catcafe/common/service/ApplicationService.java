package mabubu0203.com.github.catcafe.common.service;

import java.time.LocalDateTime;
import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;
import mabubu0203.com.github.catcafe.common.service.model.ServiceOutput;
import reactor.core.publisher.Mono;

public interface ApplicationService<I extends ServiceInput, O extends ServiceOutput> {

  Mono<O> action(I input);

  default LocalDateTime getReceptionTime() {
    return LocalDateTime.now();
  }

}
