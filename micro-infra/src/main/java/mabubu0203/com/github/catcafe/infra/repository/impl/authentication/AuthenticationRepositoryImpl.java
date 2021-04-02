package mabubu0203.com.github.catcafe.infra.repository.impl.authentication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.domain.entity.authentication.XApiKeyEntity;
import mabubu0203.com.github.catcafe.domain.repository.authentication.AuthenticationRepository;
import mabubu0203.com.github.catcafe.domain.value.XApiKeyToken;
import mabubu0203.com.github.catcafe.infra.source.redis.XApiKeySource;
import mabubu0203.com.github.catcafe.infra.source.redis.dto.XApiKey;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class AuthenticationRepositoryImpl implements AuthenticationRepository {

  private final XApiKeySource xApiKeySource;

  @Override
  public Mono<XApiKeyEntity> findOne(XApiKeyToken token, LocalDateTime receptionTime) {
    var entity =
        this.xApiKeySource.findByToken(token.value())
            .thenApply(List::stream)
            .thenApply(stream ->
                stream
                    .filter(x -> x.getStartDateTime().isBefore(receptionTime))
                    .filter(x -> x.getEndDateTime().isAfter(receptionTime))
                    .findFirst()
            )
            .thenApply(opt -> opt.map(this::convertXApiKeyEntity))
            .thenApply(Optional::get)
            .join();
    return Mono.just(entity);
  }

  @Override
  public Mono<XApiKeyEntity> generate(XApiKeyEntity entity, LocalDateTime receptionTime) {
    var xApiKey = Optional.of(entity)
        .map(this::toDto)
        .map(XApiKey.class::cast)
        .map(dto -> this.xApiKeySource.insert(dto, null))
        .orElseThrow(RuntimeException::new)
        .thenApply(this::convertXApiKeyEntity)
        .join();
    return Mono.just(xApiKey);
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