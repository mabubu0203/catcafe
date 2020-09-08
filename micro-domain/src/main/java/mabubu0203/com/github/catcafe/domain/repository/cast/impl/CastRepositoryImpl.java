package mabubu0203.com.github.catcafe.domain.repository.cast.impl;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastCatEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastEntity;
import mabubu0203.com.github.catcafe.domain.entity.cast.SearchCondition;
import mabubu0203.com.github.catcafe.domain.repository.cast.CastRepository;
import mabubu0203.com.github.catcafe.domain.value.CastCatId;
import mabubu0203.com.github.catcafe.domain.value.CastId;
import mabubu0203.com.github.catcafe.domain.value.StoreId;
import mabubu0203.com.github.catcafe.infra.source.jpa.CastCatSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.CastSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Cast;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Cast_;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class CastRepositoryImpl implements CastRepository {

    private final CastSource castSource;
    private final CastCatSource castCatSource;

    @Async
    @Override
    public CompletableFuture<Stream<CastEntity>> search(SearchCondition condition) {
        return CompletableFuture.supplyAsync(() -> this.searchImpl(condition).stream());
    }

    private List<CastEntity> searchImpl(SearchCondition condition) {
        var pageRequest = PageRequest.of(
                condition.getPage(),
                condition.getSize(),
                condition.getOptSortKey().map(Sort::by).orElse(Sort.unsorted()));
        var specification = Specification
                .where(this.storeIdInclude(condition.getOptStoreIds().orElseGet(Collections::emptyList)))
                .and(this.castIdInclude(condition.getOptCastIds().orElseGet(Collections::emptyList)));

        var castPage = this.castSource.findAll(specification, pageRequest);
        var castCatList = this.castCatSource.findAll();

        var casts = new ArrayList<CastEntity>();
        for (Cast cast : castPage) {
            var concatList = castCatList.stream()
                    .filter(castCat -> castCat.getId().equals(cast.getCastCatId()))
                    .map(castCat -> {
                        var castCatEntity = CastCatEntity.builder()
                                .castCatId(Optional.of(new CastCatId(castCat.getId())))
                                .name(castCat.getName())
                                .build();
                        return CastEntity.builder()
                                .castId(Optional.of(new CastId(cast.getId())))
                                .storeId(new StoreId(cast.getStoreId()))
                                .CastCatEntity(castCatEntity)
                                .build();
                    })
                    .collect(Collectors.toList());
            casts.addAll(concatList);
        }
        return casts;
    }

    private Specification<Cast> storeIdInclude(List<Integer> storeIds) {
        return storeIds.size() == 0 ? null : new Specification<Cast>() {
            @Override
            public Predicate toPredicate(Root<Cast> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return root.get(Cast_.storeId).in(storeIds);
            }
        };
    }

    private Specification<Cast> castIdInclude(List<Integer> castIds) {
        return castIds.size() == 0 ? null : new Specification<Cast>() {
            @Override
            public Predicate toPredicate(Root<Cast> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return root.get(Cast_.id).in(castIds);
            }
        };
    }

}
