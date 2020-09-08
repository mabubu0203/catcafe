package mabubu0203.com.github.catcafe.api.service.cast.impl;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.cast.CastSearchService;
import mabubu0203.com.github.catcafe.api.service.cast.model.input.CastSearchServiceInput;
import mabubu0203.com.github.catcafe.api.service.cast.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.infra.source.jpa.CastCatSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.CastSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Cast;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Cast_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CastSearchServiceImpl implements CastSearchService {

    private final CastSource castSource;
    private final CastCatSource castCatSource;

    @Override
    public CompletableFuture<CastSearchServiceOutput> promise(CastSearchServiceInput input) {
        return CompletableFuture.completedFuture(this.search(input));
    }

    private CastSearchServiceOutput search(CastSearchServiceInput input) {
        var casts = new ArrayList<CastSearchServiceOutput.CastObject>();
        var specification = Specification
                .where(this.storeIdInclude(input.getStoreIds().get()));

        var a = this.castSource.findAll(specification);
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

    private Specification<Cast> storeIdInclude(List<Integer> storeIds) {
        return storeIds.size() == 0 ? null : new Specification<Cast>() {
            @Override
            public Predicate toPredicate(Root<Cast> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return root.get(Cast_.storeId).in(storeIds);
            }
        };
    }
}
