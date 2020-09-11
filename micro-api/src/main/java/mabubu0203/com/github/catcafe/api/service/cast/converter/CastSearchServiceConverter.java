package mabubu0203.com.github.catcafe.api.service.cast.converter;

import mabubu0203.com.github.catcafe.api.service.cast.model.input.CastSearchServiceInput;
import mabubu0203.com.github.catcafe.domain.entity.cast.SearchCondition;

import java.util.function.Function;

public class CastSearchServiceConverter implements Function<CastSearchServiceInput, SearchCondition> {

    @Override
    public SearchCondition apply(CastSearchServiceInput castSearchServiceInput) {
        var condition = new SearchCondition();
        condition.setOptStoreIds(castSearchServiceInput.getOptStoreIds());
        condition.setOptCastIds(castSearchServiceInput.getOptCastIds());
        condition.setPage(castSearchServiceInput.getOptPage().orElse(0));
        condition.setSize(castSearchServiceInput.getOptSize().orElse(20));
        condition.setOptSortKeys(castSearchServiceInput.getOptSortKeys());
        return condition;
    }

}
