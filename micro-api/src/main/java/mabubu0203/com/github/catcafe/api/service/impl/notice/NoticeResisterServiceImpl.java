package mabubu0203.com.github.catcafe.api.service.impl.notice;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.notice.service.NoticeResisterService;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.input.NoticeResisterServiceInput;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.output.NoticeResisterServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.notice.converter.NoticeResisterServiceConverter;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeEntity;
import mabubu0203.com.github.catcafe.domain.repository.notice.NoticeRepository;
import mabubu0203.com.github.catcafe.domain.repository.store.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NoticeResisterServiceImpl implements NoticeResisterService {

  private final NoticeRepository noticeRepository;
  private final StoreRepository storeRepository;
  private final NoticeResisterServiceConverter converter = new NoticeResisterServiceConverter();

  @Override
  @Transactional
  public Mono<NoticeResisterServiceOutput> action(NoticeResisterServiceInput input) {
    var receptionTime = this.getReceptionTime();
    return Optional.of(input)
        .map(this.converter::fromInput)
        .map(this::beforeRegistration)
        .orElseThrow(RuntimeException::new)
        .flatMap(entity -> this.noticeRepository.resister(entity, receptionTime))
        .map(this.converter::toOutput);
  }

  private Mono<NoticeEntity> beforeRegistration(NoticeEntity entity) {
    return this.storeRepository.findBy(entity.getStoreId())
        .map(store -> entity);
  }

}
