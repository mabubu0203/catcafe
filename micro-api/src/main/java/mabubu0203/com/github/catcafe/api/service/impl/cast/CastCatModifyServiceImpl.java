package mabubu0203.com.github.catcafe.api.service.impl.cast;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.service.CastCatModifyService;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastCatModifyServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastCatModifyServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.cast.converter.CastCatModifyServiceConverter;
import mabubu0203.com.github.catcafe.domain.repository.cast.CastRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CastCatModifyServiceImpl implements CastCatModifyService {

  private final CastRepository castRepository;
  private final CastCatModifyServiceConverter converter = new CastCatModifyServiceConverter();

  @Override
  @Transactional
  public Mono<CastCatModifyServiceOutput> action(CastCatModifyServiceInput input) {
    var receptionTime = this.getReceptionTime();
    return Optional.of(input)
        .map(this.converter::fromInput)
        .map(entity -> this.castRepository.modify(entity, receptionTime))
        .orElseThrow(RuntimeException::new)
        .map(this.converter::toOutput);
  }

}
