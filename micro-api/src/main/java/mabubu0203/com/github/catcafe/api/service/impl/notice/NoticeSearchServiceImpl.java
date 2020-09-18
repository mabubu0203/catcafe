package mabubu0203.com.github.catcafe.api.service.impl.notice;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.notice.service.NoticeSearchService;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.input.NoticeSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.output.NoticeSearchServiceOutput;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class NoticeSearchServiceImpl implements NoticeSearchService {

    @Override
    public CompletableFuture<NoticeSearchServiceOutput> promise(NoticeSearchServiceInput input) {
        return CompletableFuture.completedFuture(new NoticeSearchServiceOutput());
    }

}
