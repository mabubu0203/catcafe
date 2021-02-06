package mabubu0203.com.github.catcafe.infra.repository.impl.authentication;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.domain.entity.authentication.XApiKeyEntity;
import mabubu0203.com.github.catcafe.domain.entity.authentication.XApiKeySearchConditions;
import mabubu0203.com.github.catcafe.domain.repository.authentication.AuthenticationRepository;
import mabubu0203.com.github.catcafe.domain.value.XApiKeyToken;
import mabubu0203.com.github.catcafe.infra.source.jpa.XApiKeySource;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.XApiKey;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.XApiKey_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class AuthenticationRepositoryImpl implements AuthenticationRepository {

    private final XApiKeySource xApiKeySource;

    @Override
    @Async
    public CompletableFuture<Stream<XApiKeyEntity>> search(XApiKeySearchConditions searchConditions) {
        var specification = Specification
                .where(this.tokenEquals(searchConditions.token()));
//                .and(this.startDateTimeAfter(searchConditions.specified_date_time()))
//                .and(this.endDateTimeBefore(searchConditions.specified_date_time()));
        return this.xApiKeySource.searchStream(specification, searchConditions.getPageRequest())
                .thenApply(stream -> stream.map(this::convertXApiKeyEntity));
    }

    private Specification<XApiKey> tokenEquals(XApiKeyToken token) {
        return token == null ? null : (root, query, builder) ->
                builder.equal(root.get(XApiKey_.token), token.value());
    }

    private Specification<XApiKey> startDateTimeAfter(LocalDateTime specified_date_time) {
        return specified_date_time == null ? null : (root, query, builder) ->
                builder.greaterThan(root.get(XApiKey_.startDateTime), specified_date_time);
    }

    private Specification<XApiKey> endDateTimeBefore(LocalDateTime specified_date_time) {
        return specified_date_time == null ? null : (root, query, builder) ->
                builder.lessThanOrEqualTo(root.get(XApiKey_.endDateTime), specified_date_time);
    }

    @Override
    @Async
    public CompletableFuture<XApiKeyEntity> generate(XApiKeyEntity entity, LocalDateTime receptionTime) {
        return CompletableFuture
                .supplyAsync(() -> entity)
                .thenApply(this::toDto)
                .thenApply(dto -> dto.setCreatedBy(0))
                .thenApply(XApiKey.class::cast)
                .thenCompose(dto -> this.xApiKeySource.insert(dto, receptionTime))
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
                .createdDateTime(dto.getCreatedDateTime())
                .endDateTime(dto.getEndDateTime())
                .build();
    }

}
