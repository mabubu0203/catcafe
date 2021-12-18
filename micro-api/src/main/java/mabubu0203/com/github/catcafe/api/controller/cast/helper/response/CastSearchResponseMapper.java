package mabubu0203.com.github.catcafe.api.controller.cast.helper.response;

import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Optional;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.SearchResponseMapper;
import org.openapitools.model.CastCatDetail;
import org.openapitools.model.CastDetail;
import org.openapitools.model.CastSearchResponse;
import org.openapitools.model.Common;

public class CastSearchResponseMapper implements
    SearchResponseMapper<CastSearchServiceOutput, CastSearchResponse> {

  @Override
  public CastSearchResponse apply(CastSearchServiceOutput castSearchServiceOutput) {
    var casts = castSearchServiceOutput.getCasts().stream()
        .map(this::convert)
        .toList();

    var result = new CastSearchResponse();
    result.setCasts(casts);
    return result;
  }

  private CastDetail convert(CastSearchServiceOutput.CastObject cast) {
    var detail = new CastDetail();
    var employmentStatus =
        CastDetail.EmploymentStatusEnum.fromValue(cast.getEmploymentStatus());
    var common = this.common(cast.getCommon());
    var castCat = this.convert(cast.getCastCat());
    detail.setId(cast.getId());
    detail.setStoreId(cast.getStoreId());
    detail.setEmploymentStatus(employmentStatus);
    detail.setFirstAttendanceDate(cast.getFirstAttendanceDate());
    detail.setLastAttendanceDate(cast.getLastAttendanceDate());
    detail.setMemo(cast.getMemo());
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
    detail.setBirthdayDate(null);
    detail.setFavorite(null);
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
