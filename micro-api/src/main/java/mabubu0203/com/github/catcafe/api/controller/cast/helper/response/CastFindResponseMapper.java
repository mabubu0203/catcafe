package mabubu0203.com.github.catcafe.api.controller.cast.helper.response;

import java.time.ZoneOffset;
import java.util.Collections;
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
    var castCat = this.convert(cast.getCastCat());
    detail.setId(cast.getId());
    detail.setStoreId(cast.getStoreId());
    detail.setEmploymentStatus(null);
    detail.setFirstAttendanceDate(cast.getFirstAttendanceDate());
    detail.setLastAttendanceDate(cast.getLastAttendanceDate());
    detail.setStoreMemo(cast.getMemo());
    detail.setCommon(common);
    detail.setCastCat(castCat);
    return detail;
  }

  private CastCatDetail convert(CastSearchServiceOutput.CastCatObject castCat) {
    var detail = new CastCatDetail();
    var common = this.common(castCat.getCommon());
    detail.setId(castCat.getId());
    detail.setName(castCat.getName());
    detail.setImage(castCat.getImage());
    detail.setType(castCat.getType());
    detail.setSex(null);
    detail.setBirthdayDate(castCat.getBirthdayDate());
    detail.setLike(null);
    detail.setDislike(null);
    detail.setProhibition(null);
    detail.setBrothers(Collections.emptyList());
    detail.setSisters(Collections.emptyList());
    detail.setMemo(castCat.getMemo());
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