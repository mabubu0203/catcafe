package mabubu0203.com.github.catcafe.infra.repository.impl.notice;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeEntity;
import mabubu0203.com.github.catcafe.domain.repository.notice.NoticeRepository;
import mabubu0203.com.github.catcafe.domain.value.NoticeId;
import mabubu0203.com.github.catcafe.infra.source.jpa.NoticeSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Notice;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Repository
@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepository {

    private final NoticeSource noticeSource;

    @Override
    @Async
    public CompletableFuture<NoticeId> resister(NoticeEntity notice) {
        return CompletableFuture.supplyAsync(() -> notice)
                .thenApply(this::toDto)
                .thenApply(dto -> dto.setCreatedDateTime(LocalDateTime.now()))
                .thenApply(dto -> dto.setCreatedBy(0))
                .thenApply(dto -> dto.setVersion(0))
                .thenApply(Notice.class::cast)
                .thenApply(this.noticeSource::save)
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
