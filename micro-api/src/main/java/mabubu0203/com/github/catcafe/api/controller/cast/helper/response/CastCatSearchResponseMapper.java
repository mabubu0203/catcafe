package mabubu0203.com.github.catcafe.api.controller.cast.helper.response;

import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Optional;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastCatSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.SearchResponseMapper;
import org.openapitools.model.CastCatDetail;
import org.openapitools.model.CastCatSearchResponse;
import org.openapitools.model.Common;

public class CastCatSearchResponseMapper implements
    SearchResponseMapper<CastCatSearchServiceOutput, CastCatSearchResponse> {

  @Override
  public CastCatSearchResponse apply(CastCatSearchServiceOutput castCatSearchServiceOutput) {
    var castCats = castCatSearchServiceOutput.getCastCats().stream()
        .map(this::convert)
        .toList();

    var result = new CastCatSearchResponse();
    result.setCastCats(castCats);
    return result;
  }

  private CastCatDetail convert(CastCatSearchServiceOutput.CastCatObject castCat) {
    var detail = new CastCatDetail();
    var sex = CastCatDetail.SexEnum.fromValue(castCat.getSex());
    var common = this.common(castCat.getCommon());
    detail.setId(castCat.getId());
    detail.setName(castCat.getName());
    detail.setImage(castCat.getImage());
    detail.setType(castCat.getType());
    detail.setSex(sex);
    detail.setBirthdayDate(castCat.getBirthdayDate());
    detail.setFavorite(castCat.getFavorite());
    detail.setDislike(castCat.getDislike());
    detail.setProhibition(castCat.getProhibition());
    detail.setBrothers(Collections.emptyList());
    detail.setSisters(Collections.emptyList());
    detail.setMemo(castCat.getMemo());
    detail.setCommon(common);
    return detail;
  }

  private Common common(CastCatSearchServiceOutput.CommonObject object) {
    var common = new Common();
    common.setCreatedDateTime(object.getCreatedDateTime().atOffset(ZoneOffset.ofHours(9)));
    common.setVersion(object.getVersion());
    Optional.ofNullable(object.getUpdatedDateTime())
        .map(ldt -> ldt.atOffset(ZoneOffset.ofHours(9)))
        .ifPresent(common::setUpdatedDateTime);
    return common;
  }

}
