package mabubu0203.com.github.catcafe.api.service.impl.notice;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.notice.service.NoticeSearchService;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.input.NoticeSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.output.NoticeSearchServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.notice.converter.NoticeSearchServiceConverter;
import mabubu0203.com.github.catcafe.domain.repository.notice.NoticeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NoticeSearchServiceImpl implements NoticeSearchService {

  private final NoticeRepository noticeRepository;
  private final NoticeSearchServiceConverter converter = new NoticeSearchServiceConverter();

  @Override
  @Transactional(readOnly = true)
  public Mono<NoticeSearchServiceOutput> action(NoticeSearchServiceInput input) {
    return Optional.of(input)
        .map(this.converter::toSearchCondition)
        .map(this.noticeRepository::search)
        .map(this.converter::toOutput)
        .orElseThrow(RuntimeException::new);
  }

}
