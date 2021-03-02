package mabubu0203.com.github.catcafe.api.service.impl.cast;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.service.CastSearchService;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.cast.converter.CastSearchServiceConverter;
import mabubu0203.com.github.catcafe.domain.repository.cast.CastRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CastSearchServiceImpl implements CastSearchService {

  private final CastRepository castRepository;
  private final CastSearchServiceConverter converter = new CastSearchServiceConverter();

  @Override
  @Async
  @Transactional(readOnly = true)
  public CompletableFuture<CastSearchServiceOutput> promise(CastSearchServiceInput input) {
    return Optional
        .of(input)
        .map(this.converter::toSearchCondition)
        .map(this.castRepository::search)
        .map(future -> future.thenApply(this.converter::toServiceOutput))
        .orElseThrow(RuntimeException::new);
  }

}
