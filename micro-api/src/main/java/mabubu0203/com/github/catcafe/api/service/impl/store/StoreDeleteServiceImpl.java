package mabubu0203.com.github.catcafe.api.service.impl.store;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.store.service.StoreDeleteService;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreDeleteServiceInput;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreDeleteServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.store.converter.StoreDeleteServiceConverter;
import mabubu0203.com.github.catcafe.domain.repository.store.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StoreDeleteServiceImpl implements StoreDeleteService {

  private final StoreRepository storeRepository;
  private final StoreDeleteServiceConverter converter = new StoreDeleteServiceConverter();

  @Override
  @Transactional
  public Mono<StoreDeleteServiceOutput> action(StoreDeleteServiceInput input) {
    var receptionTime = this.getReceptionTime();
    return Optional.of(input)
        .map(this.converter::fromInput)
        .map(entity -> this.storeRepository.logicalDelete(entity, receptionTime))
        .orElseThrow(RuntimeException::new)
        .map(this.converter::toOutput);
  }

}
