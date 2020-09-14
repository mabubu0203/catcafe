package mabubu0203.com.github.catcafe.api.service.cast.impl;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.cast.CastRegisterService;
import mabubu0203.com.github.catcafe.api.service.cast.converter.CastResisterServiceConverter;
import mabubu0203.com.github.catcafe.api.service.cast.model.input.CastRegisterServiceInput;
import mabubu0203.com.github.catcafe.api.service.cast.model.output.CastRegisterServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastEntity;
import mabubu0203.com.github.catcafe.domain.repository.cast.CastRepository;
import mabubu0203.com.github.catcafe.domain.repository.store.StoreRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class CastResisterServiceImpl implements CastRegisterService {

    private final CastRepository castRepository;
    private final StoreRepository storeRepository;
    private final CastResisterServiceConverter converter = new CastResisterServiceConverter();

    @Override
    @Async
    @Transactional
    public CompletableFuture<CastRegisterServiceOutput> promise(CastRegisterServiceInput input) {
        return Optional.of(input)
                .map(this.converter::fromInput)
                .map(this::beforeRegistration)
                .map(this.castRepository::resister)
                .map(future -> future.thenApply(this.converter::toOutput))
                .orElseThrow(RuntimeException::new);
    }

    private CastEntity beforeRegistration(CastEntity entity) {
        var store = this.storeRepository.exists(entity.getStoreId());
        var castCat = this.castRepository.exists(entity.getCastCatEntity().getCastCatId().get());

        var futureList = Arrays.asList(store, castCat);
        var futureListSize = futureList.size();
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureListSize])).join();
        var successSize = futureList.stream()
                .map(CompletableFuture::join)
                .filter(result -> result)
                .count();

        if (futureListSize == successSize) {
            return entity;
        } else {
            return null;
        }
    }

}
