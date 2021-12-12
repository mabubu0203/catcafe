package mabubu0203.com.github.catcafe.api.service.impl.cast;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.service.CastCatSearchService;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastCatSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastCatSearchServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.cast.converter.CastCatSearchServiceConverter;
import mabubu0203.com.github.catcafe.domain.repository.cast.CastRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CastCatSearchServiceImpl implements CastCatSearchService {

  private final CastRepository castRepository;
  private final CastCatSearchServiceConverter converter = new CastCatSearchServiceConverter();

  @Override
  @Transactional(readOnly = true)
  public Mono<CastCatSearchServiceOutput> action(CastCatSearchServiceInput input) {
    return Optional.of(input)
        .map(this.converter::toSearchCondition)
        .map(this.castRepository::search)
        .map(this.converter::toOutput)
        .orElseThrow(RuntimeException::new);
  }

}
