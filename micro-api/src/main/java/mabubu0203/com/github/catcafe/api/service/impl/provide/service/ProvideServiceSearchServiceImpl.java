package mabubu0203.com.github.catcafe.api.service.impl.provide.service;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.provide.service.service.ProvideServiceSearchService;
import mabubu0203.com.github.catcafe.api.controller.provide.service.service.model.input.ProvideServiceSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.provide.service.service.model.output.ProvideServiceSearchServiceOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProvideServiceSearchServiceImpl implements ProvideServiceSearchService {

  @Override
  @Transactional(readOnly = true)
  public Mono<ProvideServiceSearchServiceOutput> action(
      ProvideServiceSearchServiceInput input) {
    return null;
//    return CompletableFuture.completedFuture(new ProvideServiceSearchServiceOutput());
  }

}
