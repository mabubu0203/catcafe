package mabubu0203.com.github.catcafe.api.service.impl.cast;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.service.CastRegisterService;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastRegisterServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastRegisterServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.cast.converter.CastResisterServiceConverter;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastEntity;
import mabubu0203.com.github.catcafe.domain.repository.cast.CastRepository;
import mabubu0203.com.github.catcafe.domain.repository.store.StoreRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        var receptionTime = getReceptionTime();
        return Optional.of(input)
                .map(this.converter::fromInput)
                .map(this::beforeRegistration)
                .map(entity -> this.castRepository.resister(entity, receptionTime))
                .map(future -> future.thenApply(this.converter::toOutput))
                .orElseThrow(RuntimeException::new);
    }

    private CastEntity beforeRegistration(CastEntity entity) {
        return Optional.ofNullable(entity.getStoreId())
                .map(this.storeRepository::exists)
                .map(CompletableFuture::join)
                .filter(Boolean::booleanValue)
                .map(bool -> entity)
                .orElseThrow(() -> new RuntimeException("店舗チェックで失敗しています"));
    }

}
