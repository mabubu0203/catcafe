package mabubu0203.com.github.catcafe.api.service.impl.authentication;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.authentication.service.XApiKeyGenerateService;
import mabubu0203.com.github.catcafe.api.controller.authentication.service.model.input.XApiKeyGenerateServiceInput;
import mabubu0203.com.github.catcafe.api.controller.authentication.service.model.output.XApiKeyGenerateServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.authentication.converter.XApiKeyGenerateServiceConverter;
import mabubu0203.com.github.catcafe.domain.entity.authentication.XApiKeyEntity;
import mabubu0203.com.github.catcafe.domain.repository.authentication.AuthenticationRepository;
import mabubu0203.com.github.catcafe.domain.value.XApiKeyToken;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class XApiKeyGenerateServiceImpl implements XApiKeyGenerateService {

  private final AuthenticationRepository authenticationRepository;
  private final XApiKeyGenerateServiceConverter converter = new XApiKeyGenerateServiceConverter();

  @Override
  @Async
  public CompletableFuture<XApiKeyGenerateServiceOutput> promise(
      XApiKeyGenerateServiceInput input) {
    var receptionTime = this.getReceptionTime();
    var token = this.getToken(input.getRandom(), receptionTime);
    return
        Optional.of(input)
            .map(this.converter::fromInput)
            .map(builder -> builder.token(token))
            .map(builder -> builder.startDateTime(receptionTime))
            .map(builder -> builder.endDateTime(receptionTime.plusDays(1)))
            .map(XApiKeyEntity.XApiKeyEntityBuilder::build)
            .map(entity -> this.authenticationRepository.generate(entity, receptionTime))
            .map(future -> future.thenApply(this.converter::toOutput))
            .orElseThrow(RuntimeException::new);
  }

  private XApiKeyToken getToken(String random, LocalDateTime receptionTime) {
    var letter = Optional.ofNullable(random)
        .map(str -> receptionTime.toString().concat(str))
        .map(str -> str.getBytes(StandardCharsets.UTF_8))
        .orElse(receptionTime.toString().getBytes(StandardCharsets.UTF_8));
    var encoded = Base64.getEncoder().encodeToString(letter);
    return new XApiKeyToken(encoded);
  }

}
