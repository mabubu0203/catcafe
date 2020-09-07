package mabubu0203.com.github.catcafe.api.service.store.impl;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.store.StoreRegisterService;
import mabubu0203.com.github.catcafe.api.service.store.model.input.StoreRegisterServiceInput;
import mabubu0203.com.github.catcafe.api.service.store.model.output.StoreRegisterServiceOutput;
import mabubu0203.com.github.catcafe.infra.source.jpa.StoreSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Store;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class StoreRegisterServiceImpl implements StoreRegisterService {

    private final StoreSource source;

    @Override
    public CompletableFuture<StoreRegisterServiceOutput> promise(StoreRegisterServiceInput input) {
        var store = new Store()
                .setName(input.getName());
        store
                .setCreatedDateTime(LocalDateTime.now())
                .setCreatedBy(0)
                .setVersion(0);
        this.source.save(store);
        return null;
    }

}
