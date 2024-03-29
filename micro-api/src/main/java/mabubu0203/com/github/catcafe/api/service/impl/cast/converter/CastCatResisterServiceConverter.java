package mabubu0203.com.github.catcafe.api.service.impl.cast.converter;

import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastCatResisterServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastCatResisterServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.cast.CastCatEntity;
import mabubu0203.com.github.catcafe.domain.value.CastCatId;
import mabubu0203.com.github.catcafe.domain.value.HttpUrl;
import mabubu0203.com.github.catcafe.domain.value.Memo;
import mabubu0203.com.github.catcafe.domain.value.cast.CatSex;

public class CastCatResisterServiceConverter {

  public CastCatEntity fromInput(CastCatResisterServiceInput input) {
    var castCatId = CastCatId.emptyId();
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
        .version(null)
        .updatedDateTime(null)
        .build();
  }

  public CastCatResisterServiceOutput toOutput(CastCatId castCatId) {
    return CastCatResisterServiceOutput.builder()
        .id(castCatId.value())
        .build();
  }

}
