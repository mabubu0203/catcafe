package mabubu0203.com.github.catcafe.infra.repository.impl.authentication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.domain.entity.authentication.XApiKeyEntity;
import mabubu0203.com.github.catcafe.domain.repository.authentication.AuthenticationRepository;
import mabubu0203.com.github.catcafe.domain.value.XApiKeyToken;
import mabubu0203.com.github.catcafe.infra.source.redis.XApiKeySource;
import mabubu0203.com.github.catcafe.infra.source.redis.dto.XApiKey;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthenticationRepositoryImpl implements AuthenticationRepository {

  private final XApiKeySource xApiKeySource;

  @Override
  @Async
  public CompletableFuture<Optional<XApiKeyEntity>> findOne(XApiKeyToken token,
      LocalDateTime receptionTime) {
    return
        this.xApiKeySource.findByToken(token.value())
            .thenApply(List::stream)
            .thenApply(stream ->
                stream
                    .filter(x -> x.getStartDateTime().isBefore(receptionTime))
                    .filter(x -> x.getEndDateTime().isAfter(receptionTime))
                    .findFirst()
            )
            .thenApply(opt -> opt.map(this::convertXApiKeyEntity));
  }

  @Override
  @Async
  public CompletableFuture<XApiKeyEntity> generate(XApiKeyEntity entity,
      LocalDateTime receptionTime) {
    return CompletableFuture
        .supplyAsync(() -> entity)
        .thenApply(this::toDto)
        .thenApply(XApiKey.class::cast)
        .thenCompose(dto -> this.xApiKeySource.insert(dto, null))
        .thenApply(this::convertXApiKeyEntity);
  }

  private XApiKey toDto(XApiKeyEntity entity) {
    return new XApiKey()
        .setToken(entity.getToken().value())
        .setClientIp(entity.getClientIp())
        .setStartDateTime(entity.getStartDateTime())
        .setEndDateTime(entity.getEndDateTime());
  }

  private XApiKeyEntity convertXApiKeyEntity(XApiKey dto) {
    var token = new XApiKeyToken(dto.getToken());
    return XApiKeyEntity.builder()
        .clientIp(dto.getClientIp())
        .token(token)
        .startDateTime(dto.getStartDateTime())
        .endDateTime(dto.getEndDateTime())
        .build();
  }

}
