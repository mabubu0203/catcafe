package mabubu0203.com.github.catcafe.api.service.impl.store;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.store.service.StoreDeleteService;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.input.StoreDeleteServiceInput;
import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreDeleteServiceOutput;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class StoreDeleteServiceImpl implements StoreDeleteService {

    @Override
    @Async
    @Transactional
    public CompletableFuture<StoreDeleteServiceOutput> promise(StoreDeleteServiceInput input) {
        return null;
    }

}
