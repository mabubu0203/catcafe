package mabubu0203.com.github.catcafe.infra.repository.impl.notice;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeEntity;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeSearchConditions;
import mabubu0203.com.github.catcafe.domain.repository.notice.NoticeRepository;
import mabubu0203.com.github.catcafe.domain.value.NoticeId;
import mabubu0203.com.github.catcafe.domain.value.StoreId;
import mabubu0203.com.github.catcafe.infra.source.jpa.NoticeSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Notice;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Notice_;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepository {

    private final NoticeSource source;

    @Override
    @Async
    public CompletableFuture<Stream<NoticeEntity>> search(NoticeSearchConditions searchConditions) {
        var specification = Specification
                .where(this.storeIdInclude(searchConditions.optStoreIds()));
        return CompletableFuture.supplyAsync(() -> this.source.findAll(specification, searchConditions.getPageRequest()))
                .thenApply(Page::stream)
                .thenApply(stream -> stream.map(this::convertNoticeEntity));
    }

    private Specification<Notice> storeIdInclude(Optional<List<Integer>> optStoreIds) {
        var storeIds = optStoreIds.orElseGet(Collections::emptyList);
        return storeIds.size() == 0 ?
                null : (root, criteriaQuery, criteriaBuilder) -> root.get(Notice_.storeId).in(storeIds);
    }

    private NoticeEntity convertNoticeEntity(Notice notice) {
        var storeId = new StoreId(notice.getStoreId());
        return NoticeEntity.builder()
                .storeId(storeId)
                .build();
    }

    @Override
    @Async
    public CompletableFuture<NoticeId> resister(NoticeEntity notice) {
        return CompletableFuture.supplyAsync(() -> notice)
                .thenApply(this::toDto)
                .thenApply(dto -> dto.setCreatedDateTime(LocalDateTime.now()))
                .thenApply(dto -> dto.setCreatedBy(0))
                .thenApply(dto -> dto.setVersion(0))
                .thenApply(Notice.class::cast)
                .thenApply(this.source::save)
                .thenApply(Notice::getId)
                .thenApply(NoticeId::new);
    }

    private Notice toDto(NoticeEntity entity) {
        return new Notice()
                .setStoreId(entity.getStoreId().intValue())
                .setSummary(entity.getSummary())
                .setDetail(entity.getDetail());
    }
}
