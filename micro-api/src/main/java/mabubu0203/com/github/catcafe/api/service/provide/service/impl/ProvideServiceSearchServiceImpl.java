package mabubu0203.com.github.catcafe.api.service.provide.service.impl;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.provide.service.ProvideServiceSearchService;
import mabubu0203.com.github.catcafe.api.service.provide.service.model.input.ProvideServiceSearchServiceInput;
import mabubu0203.com.github.catcafe.api.service.provide.service.model.output.ProvideServiceSearchServiceOutput;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ProvideServiceSearchServiceImpl implements ProvideServiceSearchService {

    @Override
    public CompletableFuture<ProvideServiceSearchServiceOutput> promise(ProvideServiceSearchServiceInput input) {
        return CompletableFuture.completedFuture(new ProvideServiceSearchServiceOutput());
    }

}
