package mabubu0203.com.github.catcafe.api.service.impl.cast;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.service.CastCatResisterService;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastCatResisterServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastCatResisterServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.cast.converter.CastCatResisterServiceConverter;
import mabubu0203.com.github.catcafe.domain.repository.cast.CastRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class CastCatResisterServiceImpl implements CastCatResisterService {

    private final CastRepository castRepository;
    private final CastCatResisterServiceConverter converter = new CastCatResisterServiceConverter();

    @Override
    @Async
    @Transactional
    public CompletableFuture<CastCatResisterServiceOutput> promise(CastCatResisterServiceInput input) {
        var receptionTime = getReceptionTime();
        return Optional
                .of(input)
                .map(this.converter::fromInput)
                .map(entity -> this.castRepository.resister(entity, receptionTime))
                .map(future -> future.thenApply(this.converter::toOutput))
                .orElseThrow(RuntimeException::new);
    }

}
