package mabubu0203.com.github.catcafe.api.service.impl.store;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.store.service.StoreModifyService;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreModifyServiceInput;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreModifyServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.store.converter.StoreModifyServiceConverter;
import mabubu0203.com.github.catcafe.domain.repository.store.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StoreModifyServiceImpl implements StoreModifyService {

  private final StoreRepository storeRepository;
  private final StoreModifyServiceConverter converter = new StoreModifyServiceConverter();

  @Override
  @Transactional
  public Mono<StoreModifyServiceOutput> action(StoreModifyServiceInput input) {
    var receptionTime = this.getReceptionTime();
    return null;
  }

}
