package mabubu0203.com.github.catcafe.api.service.impl.notice;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.notice.service.NoticeResisterService;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.input.NoticeResisterServiceInput;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.output.NoticeResisterServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.notice.converter.NoticeResisterServiceConverter;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeEntity;
import mabubu0203.com.github.catcafe.domain.repository.notice.NoticeRepository;
import mabubu0203.com.github.catcafe.domain.repository.store.StoreRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeResisterServiceImpl implements NoticeResisterService {

  private final NoticeRepository noticeRepository;
  private final StoreRepository storeRepository;
  private final NoticeResisterServiceConverter converter = new NoticeResisterServiceConverter();

  @Override
  @Async
  @Transactional
  public CompletableFuture<NoticeResisterServiceOutput> promise(NoticeResisterServiceInput input) {
    var receptionTime = getReceptionTime();
    return Optional
        .of(input)
        .map(this.converter::fromInput)
        .map(this::beforeRegistration)
        .map(entity -> this.noticeRepository.resister(entity, receptionTime))
        .map(future -> future.thenApply(this.converter::toOutput))
        .orElseThrow(RuntimeException::new);
  }

  private NoticeEntity beforeRegistration(NoticeEntity entity) {
    return Optional
        .ofNullable(entity.getStoreId())
        .map(this.storeRepository::exists)
        .map(CompletableFuture::join)
        .filter(Boolean::booleanValue)
        .map(bool -> entity)
        .orElseThrow(() -> new RuntimeException("店舗チェックで失敗しています"));
  }

}
