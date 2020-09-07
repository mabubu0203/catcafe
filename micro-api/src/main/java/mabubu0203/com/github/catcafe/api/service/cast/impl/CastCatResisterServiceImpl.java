package mabubu0203.com.github.catcafe.api.service.cast.impl;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.cast.CastCatResisterService;
import mabubu0203.com.github.catcafe.api.service.cast.model.input.CastCatResisterServiceInput;
import mabubu0203.com.github.catcafe.api.service.cast.model.output.CastCatResisterServiceOutput;
import mabubu0203.com.github.catcafe.infra.source.jpa.CastCatSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.CastCat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class CastCatResisterServiceImpl implements CastCatResisterService {

    private final CastCatSource source;

    @Override
    public CompletableFuture<CastCatResisterServiceOutput> promise(CastCatResisterServiceInput input) {
        var castCat = new CastCat()
                .setName(input.getName());
        castCat
                .setCreatedDateTime(LocalDateTime.now())
                .setCreatedBy(0)
                .setVersion(0);
        this.source.save(castCat);
        return null;
    }

}