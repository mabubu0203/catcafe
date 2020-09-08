package mabubu0203.com.github.catcafe.api.service.cast.impl;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.cast.CastSearchService;
import mabubu0203.com.github.catcafe.api.service.cast.model.input.CastSearchServiceInput;
import mabubu0203.com.github.catcafe.api.service.cast.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.SearchCondition;
import mabubu0203.com.github.catcafe.domain.repository.cast.CastRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CastSearchServiceImpl implements CastSearchService {

    private final CastRepository castRepository;

    @Override
    public CompletableFuture<CastSearchServiceOutput> promise(CastSearchServiceInput input) {
        return CompletableFuture.completedFuture(this.search(input));
    }

    private CastSearchServiceOutput search(CastSearchServiceInput input) {

        var condition = new SearchCondition();
        condition.setStoreIds(input.getStoreIds());
        condition.setCastIds(input.getCastIds());
        condition.setSize(input.getSize());
        return new CastSearchServiceOutput()
                .setCasts(this.convert(this.castRepository.search(condition).join()));
    }

    private List<CastSearchServiceOutput.CastObject> convert(Stream<CastEntity> stream) {
        return stream
                .map(castEntity -> {
                            var castCatEntity = castEntity.getCastCatEntity();
                            return new CastSearchServiceOutput.CastObject()
                                    .setId(castEntity.getCastId().get().getValue())
                                    .setStoreId(castEntity.getStoreId().getValue())
                                    .setCastCat(
                                            new CastSearchServiceOutput.CastCatObject()
                                                    .setId(castCatEntity.getCastCatId().get().getValue())
                                                    .setName(castCatEntity.getName()));
                        })
                .collect(Collectors.toList());
    }

}
