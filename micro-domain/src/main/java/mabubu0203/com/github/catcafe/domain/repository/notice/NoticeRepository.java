package mabubu0203.com.github.catcafe.domain.repository.notice;

import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeEntity;
import mabubu0203.com.github.catcafe.domain.value.NoticeId;

import java.util.concurrent.CompletableFuture;

public interface NoticeRepository {

    CompletableFuture<NoticeId> resister(NoticeEntity notice);

}
