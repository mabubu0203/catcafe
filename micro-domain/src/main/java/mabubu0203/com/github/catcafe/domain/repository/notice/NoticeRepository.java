package mabubu0203.com.github.catcafe.domain.repository.notice;

import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeEntity;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeSearchConditions;
import mabubu0203.com.github.catcafe.domain.value.NoticeId;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public interface NoticeRepository {

    CompletableFuture<Stream<NoticeEntity>> search(NoticeSearchConditions searchConditions);

    CompletableFuture<NoticeId> resister(NoticeEntity notice);

}
