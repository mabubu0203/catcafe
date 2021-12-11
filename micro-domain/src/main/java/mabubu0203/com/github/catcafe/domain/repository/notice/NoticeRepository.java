package mabubu0203.com.github.catcafe.domain.repository.notice;

import java.time.LocalDateTime;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeEntity;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeSearchConditions;
import mabubu0203.com.github.catcafe.domain.value.NoticeId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NoticeRepository {

  /**
   * お知らせを複数取得する
   *
   * @param searchConditions
   * @return
   */
  Flux<NoticeEntity> search(NoticeSearchConditions searchConditions);

  /**
   * お知らせを1件登録する
   *
   * @param notice
   * @param receptionTime
   * @return
   */
  Mono<NoticeId> resister(NoticeEntity notice, LocalDateTime receptionTime);

}
