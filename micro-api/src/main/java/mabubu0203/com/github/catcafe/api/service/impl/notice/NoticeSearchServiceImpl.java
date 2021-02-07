package mabubu0203.com.github.catcafe.api.service.impl.notice;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.notice.service.NoticeSearchService;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.input.NoticeSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.output.NoticeSearchServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.notice.converter.NoticeSearchServiceConverter;
import mabubu0203.com.github.catcafe.domain.repository.notice.NoticeRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeSearchServiceImpl implements NoticeSearchService {

  private final NoticeRepository noticeRepository;
  private final NoticeSearchServiceConverter converter = new NoticeSearchServiceConverter();

  @Override
  @Async
  public CompletableFuture<NoticeSearchServiceOutput> promise(NoticeSearchServiceInput input) {
    return Optional
        .of(input)
        .map(this.converter::toSearchCondition)
        .map(this.noticeRepository::search)
        .map(future -> future.thenApply(this.converter::toServiceOutput))
        .orElseThrow(RuntimeException::new);
  }

}
