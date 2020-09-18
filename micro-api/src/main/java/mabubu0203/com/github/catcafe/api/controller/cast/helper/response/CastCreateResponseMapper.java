package mabubu0203.com.github.catcafe.api.controller.cast.helper.response;

import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastRegisterServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.CreateResponseMapper;
import org.openapitools.model.PostObject;

public class CastCreateResponseMapper implements CreateResponseMapper<CastRegisterServiceOutput, PostObject> {

    @Override
    public PostObject apply(CastRegisterServiceOutput castCreateServiceOutput) {
        return new PostObject().id(castCreateServiceOutput.getId());
    }

}