package mabubu0203.com.github.catcafe.api.service.impl.store;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.store.service.StoreSearchService;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreSearchServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.store.converter.StoreSearchServiceConverter;
import mabubu0203.com.github.catcafe.domain.repository.store.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StoreSearchServiceImpl implements StoreSearchService {

  private final StoreRepository storeRepository;
  private final StoreSearchServiceConverter converter = new StoreSearchServiceConverter();

  @Override
  @Transactional(readOnly = true)
  public Mono<StoreSearchServiceOutput> action(StoreSearchServiceInput input) {
    return Optional.of(input)
        .map(this.converter::toSearchCondition)
        .map(this.storeRepository::search)
        .map(this.converter::toOutput)
        .orElseThrow(RuntimeException::new);
  }

}
