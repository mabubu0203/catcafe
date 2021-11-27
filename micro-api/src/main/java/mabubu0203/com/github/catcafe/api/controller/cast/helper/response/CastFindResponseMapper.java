package mabubu0203.com.github.catcafe.api.controller.cast.helper.response;

import java.time.ZoneOffset;
import java.util.Optional;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.FindResponseMapper;
import org.openapitools.model.CastCatDetail;
import org.openapitools.model.CastDetail;
import org.openapitools.model.CastFindResponse;
import org.openapitools.model.Common;

public class CastFindResponseMapper implements
    FindResponseMapper<CastSearchServiceOutput, CastFindResponse> {

  @Override
  public CastFindResponse apply(CastSearchServiceOutput castSearchServiceOutput) {
    var result = new CastFindResponse();
    castSearchServiceOutput.getCasts().stream()
        .findFirst()
        .ifPresent(cast -> result.setCast(this.convert(cast)));
    return result;
  }

  private CastDetail convert(CastSearchServiceOutput.CastObject cast) {
    var detail = new CastDetail();
    var common = this.common(cast.getCommon());
    detail.setId(cast.getId());
    detail.setStoreId(cast.getStoreId());
    detail.setCastCat(this.convert(cast.getCastCat()));
    detail.setCommon(common);
    return detail;
  }

  private CastCatDetail convert(CastSearchServiceOutput.CastCatObject castCat) {
    var detail = new CastCatDetail();
    var common = this.common(castCat.getCommon());
    detail.setId(castCat.getId());
    detail.setName(castCat.getName());
    detail.setCommon(common);
    return detail;
  }

  private Common common(CastSearchServiceOutput.CommonObject object) {
    var common = new Common();
    common.setCreatedDateTime(object.getCreatedDateTime().atOffset(ZoneOffset.ofHours(9)));
    common.setVersion(object.getVersion());
    Optional.ofNullable(object.getUpdatedDateTime())
        .map(ldt -> ldt.atOffset(ZoneOffset.ofHours(9)))
        .ifPresent(common::setUpdatedDateTime);
    return common;
  }

}