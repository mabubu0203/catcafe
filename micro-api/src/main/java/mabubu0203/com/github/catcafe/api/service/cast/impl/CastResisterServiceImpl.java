package mabubu0203.com.github.catcafe.api.service.cast.impl;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.cast.CastRegisterService;
import mabubu0203.com.github.catcafe.api.service.cast.converter.CastResisterServiceConverter;
import mabubu0203.com.github.catcafe.api.service.cast.model.input.CastRegisterServiceInput;
import mabubu0203.com.github.catcafe.api.service.cast.model.output.CastRegisterServiceOutput;
import mabubu0203.com.github.catcafe.infra.source.jpa.CastSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Cast;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class CastResisterServiceImpl implements CastRegisterService {

    private final CastSource source;
    private final CastResisterServiceConverter converter = new CastResisterServiceConverter();

    @Override
    @Async
    @Transactional
    public CompletableFuture<CastRegisterServiceOutput> promise(CastRegisterServiceInput input) {
        var cast = new Cast()
                .setStoreId(input.getStoreId())
                .setCastCatId(input.getCastCatId());
        cast
                .setCreatedDateTime(LocalDateTime.now())
                .setCreatedBy(0)
                .setVersion(0);
        this.source.save(cast);
        return null;
    }

}
