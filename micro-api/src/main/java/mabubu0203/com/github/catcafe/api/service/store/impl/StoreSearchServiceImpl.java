package mabubu0203.com.github.catcafe.api.service.store.impl;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.store.StoreSearchService;
import mabubu0203.com.github.catcafe.api.service.store.converter.StoreSearchServiceConverter;
import mabubu0203.com.github.catcafe.api.service.store.model.input.StoreSearchServiceInput;
import mabubu0203.com.github.catcafe.api.service.store.model.output.StoreSearchServiceOutput;
import mabubu0203.com.github.catcafe.domain.repository.store.StoreRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class StoreSearchServiceImpl implements StoreSearchService {

    private final StoreRepository storeRepository;
    private final StoreSearchServiceConverter converter = new StoreSearchServiceConverter();

    @Override
    @Async
    public CompletableFuture<StoreSearchServiceOutput> promise(StoreSearchServiceInput input) {
        return Optional.of(input)
                .map(this.converter::toSearchCondition)
                .map(this.storeRepository::search)
                .map(result -> result.thenApply(this.converter::toServiceOutput))
                .get();
    }

}
