package mabubu0203.com.github.catcafe.infra.source.r2dbc;

import mabubu0203.com.github.catcafe.common.source.r2dbc.TableSource;
import mabubu0203.com.github.catcafe.infra.source.r2dbc.dto.table.Notice;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeSource extends TableSource<Notice, Integer> {

}
