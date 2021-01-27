package mabubu0203.com.github.catcafe.api.service.impl.authentication.converter;

import mabubu0203.com.github.catcafe.api.controller.authentication.service.model.input.XApiKeyGenerateServiceInput;
import mabubu0203.com.github.catcafe.api.controller.authentication.service.model.output.XApiKeyGenerateServiceOutput;
import mabubu0203.com.github.catcafe.domain.entity.authentication.XApiKeyEntity;

public class XApiKeyGenerateServiceConverter {

    public XApiKeyEntity.XApiKeyEntityBuilder fromInput(XApiKeyGenerateServiceInput input) {
        return XApiKeyEntity.builder()
                .clientIp(input.getClientIp());
    }

    public XApiKeyGenerateServiceOutput toOutput(XApiKeyEntity entity) {
        return XApiKeyGenerateServiceOutput.builder()
                .token(entity.getToken().value())
                .startDateTime(entity.getStartDateTime())
                .endDateTime(entity.getEndDateTime())
                .build();
    }

}
