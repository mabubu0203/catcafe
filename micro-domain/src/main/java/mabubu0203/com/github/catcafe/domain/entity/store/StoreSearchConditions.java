package mabubu0203.com.github.catcafe.domain.entity.store;

import lombok.Setter;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.entity.SearchConditions;

import java.util.List;
import java.util.Optional;

@Accessors(fluent = true)
@Setter
public class StoreSearchConditions extends SearchConditions {

    private Optional<List<Integer>> optStoreIds;

    public StoreSearchConditions(Integer page, Integer size, Optional<List<String>> optSortKeys) {
        super(page, size, optSortKeys);
    }

//    public Specification<Store> getCastSpecification() {
//        return Specification
//                .where(this.storeIdInclude());
//    }
//
//    private Specification<Store> storeIdInclude() {
//        var storeIds = this.optStoreIds.orElseGet(Collections::emptyList);
//        return storeIds.size() == 0 ? null : new Specification<Store>() {
//            @Override
//            public Predicate toPredicate(Root<Store> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                return root.get(Store_.id).in(storeIds);
//            }
//        };
//    }

}
