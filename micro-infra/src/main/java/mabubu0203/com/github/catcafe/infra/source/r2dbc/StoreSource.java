package mabubu0203.com.github.catcafe.infra.source.r2dbc;

import mabubu0203.com.github.catcafe.common.source.r2dbc.TableSource;
import mabubu0203.com.github.catcafe.infra.source.r2dbc.dto.table.Store;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreSource extends TableSource<Store, Integer> {

}
