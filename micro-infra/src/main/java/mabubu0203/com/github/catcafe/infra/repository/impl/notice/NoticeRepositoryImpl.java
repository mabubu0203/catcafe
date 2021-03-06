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

  @Override
  public Mono<NoticeId> resister(NoticeEntity entity, LocalDateTime receptionTime) {
    return Optional.of(entity)
        .map(this::toDto)
        .map(dto -> dto.setCreatedBy(0))
        .map(Notice.class::cast)
        .map(dto -> this.source.insert(dto, receptionTime))
        .orElseThrow(RuntimeException::new)
        .map(Notice::getId)
        .map(NoticeId::new);
  }

  private Notice toDto(NoticeEntity entity) {
    var noticeId = Optional.ofNullable(entity.getNoticeId())
        .map(NoticeId::intValue)
        .orElse(null);
    var storeId = Optional.ofNullable(entity.getStoreId())
        .map(StoreId::intValue)
        .orElse(null);
    return new Notice()
        .setId(noticeId)
        .setStoreId(storeId)
        .setSummary(entity.getSummary())
        .setDetail(entity.getDetail())
        .setPublicationStartDateTime(entity.getPublicationStartDateTime())
        .setPublicationEndDateTime(entity.getPublicationEndDateTime());
  }

}
