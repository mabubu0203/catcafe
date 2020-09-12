package mabubu0203.com.github.catcafe.domain.entity.cast;

import lombok.Setter;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.entity.SearchConditions;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Cast;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Cast_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Accessors(fluent = true)
@Setter
public class CastSearchConditions extends SearchConditions {

    private Optional<List<Integer>> optStoreIds;
    private Optional<List<Integer>> optCastIds;

    public CastSearchConditions(Integer page, Integer size, Optional<List<String>> optSortKeys) {
        super(page, size, optSortKeys);
    }

    public Specification<Cast> getCastSpecification() {
        return Specification
                .where(this.storeIdInclude())
                .and(this.castIdInclude());
    }

    private Specification<Cast> storeIdInclude() {
        var storeIds = this.optStoreIds.orElseGet(Collections::emptyList);
        return storeIds.size() == 0 ? null : new Specification<Cast>() {
            @Override
            public Predicate toPredicate(Root<Cast> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return root.get(Cast_.storeId).in(storeIds);
            }
        };
    }

    private Specification<Cast> castIdInclude() {
        var castIds = this.optCastIds.orElseGet(Collections::emptyList);
        return castIds.size() == 0 ? null : new Specification<Cast>() {
            @Override
            public Predicate toPredicate(Root<Cast> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return root.get(Cast_.id).in(castIds);
            }
        };
    }

}
