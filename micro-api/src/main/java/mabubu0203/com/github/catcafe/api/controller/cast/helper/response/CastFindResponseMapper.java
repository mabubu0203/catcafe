package mabubu0203.com.github.catcafe.api.controller.cast.helper.response;

import java.time.ZoneOffset;
import java.util.Optional;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.FindResponseMapper;
import org.openapitools.model.CastCat;
import org.openapitools.model.CastDetail;
import org.openapitools.model.CastFindResponse;
import org.openapitools.model.Common;

public class CastFindResponseMapper implements
    FindResponseMapper<CastSearchServiceOutput, CastFindResponse> {

  @Override
  public CastFindResponse apply(CastSearchServiceOutput castSearchServiceOutput) {
    var result = new CastFindResponse();
    result.setCast(this.convert(castSearchServiceOutput.getCasts().get(0)));
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