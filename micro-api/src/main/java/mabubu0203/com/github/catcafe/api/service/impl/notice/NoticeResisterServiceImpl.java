package mabubu0203.com.github.catcafe.api.service.impl.notice;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.notice.service.NoticeResisterService;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.input.NoticeResisterServiceInput;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.output.NoticeResisterServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.notice.converter.NoticeResisterServiceConverter;
import mabubu0203.com.github.catcafe.domain.entity.notice.NoticeEntity;
import mabubu0203.com.github.catcafe.domain.repository.notice.NoticeRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class NoticeResisterServiceImpl implements NoticeResisterService {

    private final NoticeRepository noticeRepository;
    private final NoticeResisterServiceConverter converter = new NoticeResisterServiceConverter();

    @Override
    @Async
    @Transactional
    public CompletableFuture<NoticeResisterServiceOutput> promise(NoticeResisterServiceInput input) {
        return Optional.of(input)
                .map(this.converter::fromInput)
                .map(this::beforeRegistration)
                .map(this.noticeRepository::resister)
                .map(future -> future.thenApply(this.converter::toOutput))
                .orElseThrow(RuntimeException::new);
    }

    private NoticeEntity beforeRegistration(NoticeEntity entity) {
        // TODO: storeの存在チェック
        return entity;
    }

}
