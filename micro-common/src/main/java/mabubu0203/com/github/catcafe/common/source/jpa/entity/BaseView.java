package mabubu0203.com.github.catcafe.common.source.jpa.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.MappedSuperclass;

@Accessors(chain = true)
@Data
@MappedSuperclass
public abstract class BaseView {
}
