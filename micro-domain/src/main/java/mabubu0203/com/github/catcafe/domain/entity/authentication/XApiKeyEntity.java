package mabubu0203.com.github.catcafe.domain.entity.authentication;

import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.domain.value.XApiKeyToken;

import java.time.LocalDateTime;

@Builder
@Getter
public class XApiKeyEntity {

    private final XApiKeyToken token;
    private final String clientIp;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final LocalDateTime createdDateTime;
    private final Integer version;
    private final LocalDateTime updatedDateTime;

}
