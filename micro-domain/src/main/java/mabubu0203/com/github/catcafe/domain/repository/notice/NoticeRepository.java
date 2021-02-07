package mabubu0203.com.github.catcafe.domain.repository.notice;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeEntity;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeSearchConditions;
import mabubu0203.com.github.catcafe.domain.value.NoticeId;

public interface NoticeRepository {

  CompletableFuture<Stream<NoticeEntity>> search(NoticeSearchConditions searchConditions);

  CompletableFuture<NoticeId> resister(NoticeEntity notice, LocalDateTime receptionTime);

}
