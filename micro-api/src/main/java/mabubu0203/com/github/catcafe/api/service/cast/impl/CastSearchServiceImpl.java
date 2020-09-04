package mabubu0203.com.github.catcafe.api.service.cast.impl;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.cast.CastSearchService;
import org.openapitools.model.CastDetail;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CastSearchServiceImpl implements CastSearchService {

    @Override
    public List<CastDetail> search() {
        var resultRest = new ArrayList<CastDetail>();
        var detail = new CastDetail();
        detail.setId(1L);
        resultRest.add(detail);

        return resultRest;
    }

}
