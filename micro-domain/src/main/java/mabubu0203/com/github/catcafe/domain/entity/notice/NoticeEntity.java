package mabubu0203.com.github.catcafe.domain.entity.notice;

import lombok.Builder;
import lombok.Getter;
import mabubu0203.com.github.catcafe.domain.value.NoticeId;
import mabubu0203.com.github.catcafe.domain.value.StoreId;

import java.time.LocalDateTime;

@Builder
@Getter
public class NoticeEntity {

    private final NoticeId noticeId;
    private final StoreId storeId;
    private final String summary;
    private final String detail;
    private final LocalDateTime createdDateTime;
    private final Integer version;
    private final LocalDateTime updatedDateTime;

}
