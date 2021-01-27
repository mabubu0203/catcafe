package mabubu0203.com.github.catcafe.api.service.impl.authentication;

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
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class XApiKeyGenerateServiceImpl implements XApiKeyGenerateService {

    private final AuthenticationRepository authenticationRepository;
    private final XApiKeyGenerateServiceConverter converter = new XApiKeyGenerateServiceConverter();

    @Override
    @Async
    @Transactional
    public CompletableFuture<XApiKeyGenerateServiceOutput> promise(XApiKeyGenerateServiceInput input) {
        var receptionTime = getReceptionTime();
        var letter = receptionTime.toString() + Optional.ofNullable(input.getRandom()).orElse("");
        var encoded = Base64.getEncoder().encodeToString(letter.getBytes());
        return Optional
                .of(input)
                .map(this.converter::fromInput)
                .map(builder -> builder.token(new XApiKeyToken(encoded)))
                .map(builder -> builder.startDateTime(receptionTime))
                .map(builder -> builder.endDateTime(receptionTime.plusDays(1)))
                .map(XApiKeyEntity.XApiKeyEntityBuilder::build)
                .map(entity -> this.authenticationRepository.generate(entity, receptionTime))
                .map(future -> future.thenApply(this.converter::toOutput))
                .orElseThrow(RuntimeException::new);
    }

}
