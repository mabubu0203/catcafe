package mabubu0203.com.github.catcafe.common.source.jpa.dto;

import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@MappedSuperclass
public abstract class BaseView {

}
