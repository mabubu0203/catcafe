package mabubu0203.com.github.catcafe.api.service.notice.impl;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.notice.NoticeSearchService;
import mabubu0203.com.github.catcafe.api.service.notice.model.input.NoticeSearchServiceInput;
import mabubu0203.com.github.catcafe.api.service.notice.model.output.NoticeSearchServiceOutput;
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
