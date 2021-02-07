package mabubu0203.com.github.catcafe.api.controller.cast.helper.response;

import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastCatResisterServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.CreateResponseMapper;
import org.openapitools.model.PostObject;

public class CastCatCreateResponseMapper implements
    CreateResponseMapper<CastCatResisterServiceOutput, PostObject> {

  @Override
  public PostObject apply(CastCatResisterServiceOutput castCatResisterServiceOutput) {
    return new PostObject().id(castCatResisterServiceOutput.getId());
  }

}
