package mabubu0203.com.github.catcafe.domain.repository.notice;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeEntity;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeSearchConditions;
import mabubu0203.com.github.catcafe.domain.value.NoticeId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NoticeRepository {

  Flux<NoticeEntity> search(NoticeSearchConditions searchConditions);

  Mono<NoticeId> resister(NoticeEntity notice, LocalDateTime receptionTime);

}
