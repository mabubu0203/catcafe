package mabubu0203.com.github.catcafe.infra.source.jpa;

import mabubu0203.com.github.catcafe.common.source.jpa.TableSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.entity.table.Notice;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeSource extends TableSource<Notice, Integer> {

}
