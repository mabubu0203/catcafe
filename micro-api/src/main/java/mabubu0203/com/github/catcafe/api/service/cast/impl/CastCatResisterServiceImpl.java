package mabubu0203.com.github.catcafe.api.service.cast.impl;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.cast.CastCatResisterService;
import mabubu0203.com.github.catcafe.api.service.cast.converter.CastCatResisterServiceConverter;
import mabubu0203.com.github.catcafe.api.service.cast.model.input.CastCatResisterServiceInput;
import mabubu0203.com.github.catcafe.api.service.cast.model.output.CastCatResisterServiceOutput;
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
        return Optional.of(input)
                .map(this.converter::fromInput)
                .map(this.castRepository::resister)
                .map(future -> future.thenApply(this.converter::toOutput))
                .get();
    }

}
