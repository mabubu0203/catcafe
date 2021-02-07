package mabubu0203.com.github.catcafe.common.controller.mapper.response;

import java.util.function.Function;
import mabubu0203.com.github.catcafe.common.service.model.ServiceOutput;

public interface CreateResponseMapper<O extends ServiceOutput, R> extends Function<O, R> {

}
