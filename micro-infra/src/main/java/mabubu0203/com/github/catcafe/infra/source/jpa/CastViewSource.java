package mabubu0203.com.github.catcafe.infra.source.jpa;

import mabubu0203.com.github.catcafe.common.source.jpa.ViewSource;
import mabubu0203.com.github.catcafe.infra.source.jpa.dto.view.CastView;
import org.springframework.stereotype.Repository;

@Repository
public interface CastViewSource extends ViewSource<CastView, String> {

}
