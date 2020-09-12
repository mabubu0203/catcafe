package mabubu0203.com.github.catcafe.api.service.cast.impl;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.cast.CastSearchService;
import mabubu0203.com.github.catcafe.api.service.cast.converter.CastSearchServiceConverter;
import mabubu0203.com.github.catcafe.api.service.cast.model.input.CastSearchServiceInput;
import mabubu0203.com.github.catcafe.api.service.cast.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.domain.repository.cast.CastRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class CastSearchServiceImpl implements CastSearchService {

    private final CastRepository castRepository;
    private final CastSearchServiceConverter converter = new CastSearchServiceConverter();

    @Override
    @Async
    public CompletableFuture<CastSearchServiceOutput> promise(CastSearchServiceInput input) {
        return Optional.of(input)
                .map(this.converter::toSearchCondition)
                .map(this.castRepository::search)
                .map(result -> result.thenApply(this.converter::toServiceOutput))
                .get();
    }

}
