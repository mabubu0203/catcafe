package mabubu0203.com.github.catcafe.api.service.impl.cast;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.service.CastRegisterService;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastRegisterServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastRegisterServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.cast.converter.CastResisterServiceConverter;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastEntity;
import mabubu0203.com.github.catcafe.domain.repository.cast.CastRepository;
import mabubu0203.com.github.catcafe.domain.repository.store.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CastResisterServiceImpl implements CastRegisterService {

  private final CastRepository castRepository;
  private final StoreRepository storeRepository;
  private final CastResisterServiceConverter converter = new CastResisterServiceConverter();

  @Override
  @Transactional
  public Mono<CastRegisterServiceOutput> action(CastRegisterServiceInput input) {
    var receptionTime = this.getReceptionTime();
    return Optional.of(input)
        .map(this.converter::fromInput)
        .map(this::beforeRegistration)
        .orElseThrow(RuntimeException::new)
        .flatMap(entity -> this.castRepository.resister(entity, receptionTime))
        .map(this.converter::toOutput);
  }

  private Mono<CastEntity> beforeRegistration(CastEntity entity) {
    return this.storeRepository.findBy(entity.getStoreId())
        .map(store -> entity);
  }

}
