package mabubu0203.com.github.catcafe.api.service.cast.impl;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.cast.CastSearchService;
import mabubu0203.com.github.catcafe.api.service.cast.model.input.CastSearchServiceInput;
import mabubu0203.com.github.catcafe.api.service.cast.model.output.CastSearchServiceOutput;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class CastSearchServiceImpl implements CastSearchService {

    @Override
    public CompletableFuture<CastSearchServiceOutput> promise(CastSearchServiceInput input) {
        return CompletableFuture.completedFuture(new CastSearchServiceOutput());
    }

}
