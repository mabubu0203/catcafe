package mabubu0203.com.github.catcafe.api.controller.cast.helper.response;

import java.time.ZoneOffset;
import java.util.Optional;
import java.util.stream.Collectors;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.SearchResponseMapper;
import org.openapitools.model.CastCat;
import org.openapitools.model.CastDetail;
import org.openapitools.model.CastSearchResponse;
import org.openapitools.model.Common;

public class CastSearchResponseMapper implements
    SearchResponseMapper<CastSearchServiceOutput, CastSearchResponse> {

  @Override
  public CastSearchResponse apply(CastSearchServiceOutput castSearchServiceOutput) {
    var casts = castSearchServiceOutput.getCasts().stream()
        .map(this::convert)
        .collect(Collectors.toList());

    var result = new CastSearchResponse();
    result.setCasts(casts);
    return result;
  }

  private CastDetail convert(CastSearchServiceOutput.CastObject cast) {
    var detail = new CastDetail();
    detail.setId(cast.getId());
    detail.setStoreId(cast.getStoreId());
    detail.setCastCat(this.convert(cast.getCastCat()));

    var common = new Common();
    var commonObject = cast.getCommon();
    common.setCreatedDateTime(null);
    common.setVersion(commonObject.getVersion());
    common.setUpdatedDateTime(
        Optional.ofNullable(commonObject.getUpdatedDateTime())
            .map(ldt -> ldt.atOffset(ZoneOffset.ofHours(9)))
            .orElse(null));
    detail.setCommon(common);
    return detail;
  }

  private CastCat convert(CastSearchServiceOutput.CastCatObject castCat) {
    var detail = new CastCat();
    detail.setId(castCat.getId());
    detail.setName(castCat.getName());

    var common = new Common();
    var commonObject = castCat.getCommon();
    common.setCreatedDateTime(null);
    common.setVersion(commonObject.getVersion());
    common.setUpdatedDateTime(
        Optional.ofNullable(commonObject.getUpdatedDateTime())
            .map(ldt -> ldt.atOffset(ZoneOffset.ofHours(9)))
            .orElse(null));
    detail.setCommon(common);
    return detail;
  }

}
