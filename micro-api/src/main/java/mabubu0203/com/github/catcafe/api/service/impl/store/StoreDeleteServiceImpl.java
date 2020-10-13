package mabubu0203.com.github.catcafe.api.service.impl.store;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.store.service.StoreDeleteService;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreDeleteServiceInput;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreDeleteServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.store.converter.StoreDeleteServiceConverter;
import mabubu0203.com.github.catcafe.domain.repository.store.StoreRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class StoreDeleteServiceImpl implements StoreDeleteService {

    private final StoreRepository storeRepository;
    private final StoreDeleteServiceConverter converter = new StoreDeleteServiceConverter();

    @Override
    @Async
    @Transactional
    public CompletableFuture<StoreDeleteServiceOutput> promise(StoreDeleteServiceInput input) {
        var receptionTime = getReceptionTime();
        return Optional
                .of(input)
                .map(this.converter::fromInput)
                .map(entity -> this.storeRepository.logicalDelete(entity, receptionTime))
                .map(future -> future.thenApply(this.converter::toOutput))
                .orElseThrow(RuntimeException::new);
    }

}
