package mabubu0203.com.github.catcafe.api.controller.authentication.service.model.output;

import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.common.service.model.ServiceOutput;

import java.time.LocalDateTime;

@Builder
@Getter
public class XApiKeyGenerateServiceOutput implements ServiceOutput {

    private final String token;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

}
