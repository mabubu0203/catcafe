package mabubu0203.com.github.catcafe.api.controller.notice.mapper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.notice.model.input.NoticeSearchServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.SearchRequestMapper;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class NoticeSearchRequestMapper implements SearchRequestMapper<NoticeSearchServiceInput> {

    private final String cats;
    private final List<Integer> storeIds;

    @Override
    public Optional<NoticeSearchServiceInput> get() {
        return Optional.of(new NoticeSearchServiceInput());
    }

}