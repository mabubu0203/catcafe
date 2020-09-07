package mabubu0203.com.github.catcafe.api.service.cast.impl;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.cast.CastSearchService;
import mabubu0203.com.github.catcafe.api.service.cast.model.input.CastSearchServiceInput;
import mabubu0203.com.github.catcafe.api.service.cast.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.infra.source.jpa.CastCatSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.CastSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Cast;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CastSearchServiceImpl implements CastSearchService {

    private final CastSource castSource;
    private final CastCatSource castCatSource;

    @Override
    public CompletableFuture<CastSearchServiceOutput> promise(CastSearchServiceInput input) {
        return CompletableFuture.completedFuture(this.search());
    }

    private CastSearchServiceOutput search() {
        var casts = new ArrayList<CastSearchServiceOutput.CastObject>();

        var a = this.castSource.findAll();
        var b = this.castCatSource.findAll();
        for (Cast cast : a) {
            var concatList = b.stream()
                    .filter(castCat -> castCat.getId().equals(cast.getCastCatId()))
                    .map(castCat -> {
                        var castObject = new CastSearchServiceOutput.CastObject();
                        var castCatObject = new CastSearchServiceOutput.CastCatObject()
                                .setId(castCat.getId())
                                .setName(castCat.getName());
                        castObject
                                .setId(cast.getId())
                                .setStoreId(cast.getStoreId())
                                .setCastCat(castCatObject);
                        return castObject;
                    })
                    .collect(Collectors.toList());
            casts.addAll(concatList);
        }
        var result = new CastSearchServiceOutput();
        result.setCasts(casts);
        return result;
    }
}
