package mabubu0203.com.github.catcafe.api.service.impl.provide.service;

import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.provide.service.service.ProvideServiceSearchService;
import mabubu0203.com.github.catcafe.api.controller.provide.service.service.model.input.ProvideServiceSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.provide.service.service.model.output.ProvideServiceSearchServiceOutput;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProvideServiceSearchServiceImpl implements ProvideServiceSearchService {

  @Override
  @Async
  @Transactional(readOnly = true)
  public CompletableFuture<ProvideServiceSearchServiceOutput> promise(
      ProvideServiceSearchServiceInput input) {
    return CompletableFuture.completedFuture(new ProvideServiceSearchServiceOutput());
  }

}
