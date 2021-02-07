package mabubu0203.com.github.catcafe.api.controller.authentication.helper.response;

import mabubu0203.com.github.catcafe.api.controller.authentication.service.model.output.XApiKeyGenerateServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.CreateResponseMapper;
import org.openapitools.model.XApiKey;
import org.openapitools.model.XApiKeyGenerateResponse;

public class XApiKeyGenerateResponseMapper implements CreateResponseMapper<XApiKeyGenerateServiceOutput, XApiKeyGenerateResponse> {

    @Override
    public XApiKeyGenerateResponse apply(XApiKeyGenerateServiceOutput xApiKeyGenerateServiceOutput) {

        var xApiKey = new XApiKey();
        xApiKey.setToken(xApiKeyGenerateServiceOutput.getToken());
//        xApiKey.setStartDateTime(xApiKeyGenerateServiceOutput.getStartDateTime().atOffset(ZoneOffset.ofHours(9)));
//        xApiKey.setEndDateTime(xApiKeyGenerateServiceOutput.getEndDateTime().atOffset(ZoneOffset.ofHours(9)));
        var result = new XApiKeyGenerateResponse();
        result.setxApiKey(xApiKey);
        return result;
    }

}
