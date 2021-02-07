package mabubu0203.com.github.catcafe.common.service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;
import mabubu0203.com.github.catcafe.common.service.model.ServiceOutput;

public interface ApplicationService<I extends ServiceInput, O extends ServiceOutput> {

  CompletableFuture<O> promise(I input);

  default LocalDateTime getReceptionTime() {
    return LocalDateTime.now();
  }

}
