package mabubu0203.com.github.catcafe.api.service.store.impl;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.store.StoreRegisterService;
import mabubu0203.com.github.catcafe.api.service.store.converter.StoreRegisterServiceConverter;
import mabubu0203.com.github.catcafe.api.service.store.model.input.StoreRegisterServiceInput;
import mabubu0203.com.github.catcafe.api.service.store.model.output.StoreRegisterServiceOutput;
import mabubu0203.com.github.catcafe.domain.repository.store.StoreRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class StoreRegisterServiceImpl implements StoreRegisterService {

    private final StoreRepository storeRepository;
    private final StoreRegisterServiceConverter converter = new StoreRegisterServiceConverter();

    @Override
    @Async
    @Transactional
    public CompletableFuture<StoreRegisterServiceOutput> promise(StoreRegisterServiceInput input) {
        return Optional.of(input)
                .map(this.converter::fromInput)
                .map(this.storeRepository::resister)
                .map(future -> future.thenApply(this.converter::toOutput))
                .orElseThrow(RuntimeException::new);
    }

}
