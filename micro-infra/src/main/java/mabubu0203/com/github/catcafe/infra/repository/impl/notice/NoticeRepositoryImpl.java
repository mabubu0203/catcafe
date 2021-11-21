package mabubu0203.com.github.catcafe.infra.repository.impl.notice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.common.source.r2dbc.dto.BaseTable;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeEntity;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeSearchConditions;
import mabubu0203.com.github.catcafe.domain.repository.notice.NoticeRepository;
import mabubu0203.com.github.catcafe.domain.value.NoticeId;
import mabubu0203.com.github.catcafe.domain.value.StoreId;
import mabubu0203.com.github.catcafe.infra.source.r2dbc.NoticeSource;
import mabubu0203.com.github.catcafe.infra.source.r2dbc.dto.table.Notice;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepository {

  private final NoticeSource source;

  @Override
  public Flux<NoticeEntity> search(NoticeSearchConditions searchConditions) {
    Predicate<Notice> storeIdInclude = notice -> {
      var storeIds = searchConditions.optStoreIds().orElseGet(Collections::emptyList);
      return storeIds.size() == 0 || storeIds.contains(notice.getStoreId());
    };
    Predicate<Notice> noticeIdInclude = notice -> {
      var noticeIds = searchConditions.optNoticeIds().orElseGet(Collections::emptyList);
      return noticeIds.size() == 0 || noticeIds.contains(notice.getId());
    };
    return this.source.findAll()
        .filter(BaseTable::isExists)
        .filter(storeIdInclude)
        .filter(noticeIdInclude)
        .map(this::convertNoticeEntity);
  }

  @Override
  public Mono<NoticeId> resister(NoticeEntity entity, LocalDateTime receptionTime) {
    return Optional.of(entity)
        .map(this::attach)
        .map(dto -> dto.setCreatedBy(0))
        .map(Notice.class::cast)
        .map(dto -> this.source.insert(dto, receptionTime))
        .orElseThrow(RuntimeException::new)
        .mapNotNull(Notice::getId)
        .map(NoticeId::new);
  }

  private NoticeEntity convertNoticeEntity(Notice dto) {
    var noticeId = new NoticeId(dto.getId());
    var storeId = new StoreId(dto.getStoreId());
    return NoticeEntity.builder()
        .noticeId(noticeId)
        .storeId(storeId)
        .summary(dto.getSummary())
        .detail(dto.getDetail())
        .publicationStartDateTime(dto.getPublicationStartDateTime())
        .publicationEndDateTime(dto.getPublicationEndDateTime())
        .createdDateTime(dto.getCreatedDateTime())
        .version(dto.getVersion())
        .updatedDateTime(dto.getUpdatedDateTime())
        .build();
  }

  private Notice attach(NoticeEntity entity) {
    return this.attach(null, entity);
  }

  private Notice attach(Notice dto, NoticeEntity entity) {
    return Optional.ofNullable(dto)
        .orElse(new Notice())
        .setId(entity.getNoticeIdValue())
        .setStoreId(entity.getStoreIdValue())
        .setSummary(entity.getSummary())
        .setDetail(entity.getDetail())
        .setPublicationStartDateTime(entity.getPublicationStartDateTime())
        .setPublicationEndDateTime(entity.getPublicationEndDateTime());
  }

}
