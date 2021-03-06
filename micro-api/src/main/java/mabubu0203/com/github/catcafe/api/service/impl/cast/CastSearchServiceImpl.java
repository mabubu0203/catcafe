package mabubu0203.com.github.catcafe.api.service.impl.cast;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.service.CastSearchService;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.cast.converter.CastSearchServiceConverter;
import mabubu0203.com.github.catcafe.domain.repository.cast.CastRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CastSearchServiceImpl implements CastSearchService {

  private final CastRepository castRepository;
  private final CastSearchServiceConverter converter = new CastSearchServiceConverter();

  @Override
  @Transactional(readOnly = true)
  public Mono<CastSearchServiceOutput> action(CastSearchServiceInput input) {
    return Optional.of(input)
        .map(this.converter::toSearchCondition)
        .map(this.castRepository::search)
        .map(this.converter::toOutput)
        .orElseThrow(RuntimeException::new);
  }

}
