package mabubu0203.com.github.catcafe.api.controller.provide.service.helper.response;

import java.util.ArrayList;
import mabubu0203.com.github.catcafe.api.controller.provide.service.service.model.output.ProvideServiceSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.SearchResponseMapper;
import org.openapitools.model.ProvideServiceDetail;
import org.openapitools.model.ProvideServiceSearchResponse;

public class ProvideServiceSearchResponseMapper implements
    SearchResponseMapper<ProvideServiceSearchServiceOutput, ProvideServiceSearchResponse> {

  @Override
  public ProvideServiceSearchResponse apply(
      ProvideServiceSearchServiceOutput provideServiceSearchServiceOutput) {
    var provideServices = new ArrayList<ProvideServiceDetail>();

    var detail = new ProvideServiceDetail();
    detail.setId(1);
    detail.setName("おやつ");
    detail.setPrice("3200");
    provideServices.add(detail);

    var result = new ProvideServiceSearchResponse();
    result.setProvideServices(provideServices);
    return result;
  }

}
