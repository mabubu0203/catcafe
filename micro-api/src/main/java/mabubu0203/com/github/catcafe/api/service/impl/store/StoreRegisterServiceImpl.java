package mabubu0203.com.github.catcafe.api.service.impl.store;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.store.service.StoreRegisterService;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreRegisterServiceInput;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreRegisterServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.store.converter.StoreRegisterServiceConverter;
import mabubu0203.com.github.catcafe.domain.repository.store.StoreRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreRegisterServiceImpl implements StoreRegisterService {

  private final StoreRepository storeRepository;
  private final StoreRegisterServiceConverter converter = new StoreRegisterServiceConverter();

  @Override
  @Async
  @Transactional
  public CompletableFuture<StoreRegisterServiceOutput> promise(StoreRegisterServiceInput input) {
    var receptionTime = getReceptionTime();
    return Optional
        .of(input)
        .map(this.converter::fromInput)
        .map(entity -> this.storeRepository.resister(entity, receptionTime))
        .map(future -> future.thenApply(this.converter::toOutput))
        .orElseThrow(RuntimeException::new);
  }

}
