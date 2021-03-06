package mabubu0203.com.github.catcafe.api.service.impl.store;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.store.service.StoreRegisterService;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreRegisterServiceInput;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreRegisterServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.store.converter.StoreRegisterServiceConverter;
import mabubu0203.com.github.catcafe.domain.repository.store.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StoreRegisterServiceImpl implements StoreRegisterService {

  private final StoreRepository storeRepository;
  private final StoreRegisterServiceConverter converter = new StoreRegisterServiceConverter();

  @Override
  @Transactional
  public Mono<StoreRegisterServiceOutput> action(StoreRegisterServiceInput input) {
    var receptionTime = this.getReceptionTime();
    return Optional.of(input)
        .map(this.converter::fromInput)
        .map(entity -> this.storeRepository.resister(entity, receptionTime))
        .orElseThrow(RuntimeException::new)
        .map(this.converter::toOutput);
  }

}
