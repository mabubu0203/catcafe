package mabubu0203.com.github.catcafe.api.controller.notice.helper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.notice.service.model.input.NoticeSearchServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.SearchRequestMapper;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class NoticeSearchRequestMapper implements SearchRequestMapper<NoticeSearchServiceInput> {

    private final String cats;
    private final List<Integer> storeIds;

    @Override
    public Mono<NoticeSearchServiceInput> get() {
        return Mono.just(new NoticeSearchServiceInput());
    }

}