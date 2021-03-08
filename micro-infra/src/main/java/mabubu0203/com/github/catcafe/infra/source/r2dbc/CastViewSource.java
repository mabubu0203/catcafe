package mabubu0203.com.github.catcafe.infra.source.r2dbc;

import mabubu0203.com.github.catcafe.common.source.r2dbc.ViewSource;
import mabubu0203.com.github.catcafe.infra.source.r2dbc.dto.view.CastView;
import org.springframework.stereotype.Repository;

@Repository
public interface CastViewSource extends ViewSource<CastView, String> {

}
