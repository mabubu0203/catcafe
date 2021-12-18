package mabubu0203.com.github.catcafe.api.service.impl.cast.converter;

import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastCatModifyServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastCatModifyServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastCatEntity;
import mabubu0203.com.github.catcafe.domain.value.CastCatId;
import mabubu0203.com.github.catcafe.domain.value.HttpUrl;
import mabubu0203.com.github.catcafe.domain.value.Memo;
import mabubu0203.com.github.catcafe.domain.value.cast.CatSex;

public class CastCatModifyServiceConverter {

  public CastCatEntity fromInput(CastCatModifyServiceInput input) {
    var castCatId = new CastCatId(input.getCastCatId());
    var sex = CatSex.getByLabel(input.getSex());
    var image = new HttpUrl(input.getImage());
    var memo = new Memo(input.getMemo());
    return CastCatEntity.builder()
        .castCatId(castCatId)
        .name(input.getName())
        .image(image)
        .type(input.getType())
        .sex(sex)
        .birthdayDate(input.getBirthdayDate())
        .favorite(input.getFavorite())
        .dislike(input.getDislike())
        .prohibition(input.getProhibition())
        // TODO
//        .brothers()
//        .sisters()
        .memo(memo)
        .createdDateTime(null)
        .version(input.getVersion())
        .updatedDateTime(null)
        .build();
  }

  public CastCatModifyServiceOutput toOutput(CastCatId castCatId) {
    return CastCatModifyServiceOutput.builder()
        .id(castCatId.value()).build();
  }

}
