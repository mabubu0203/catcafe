package mabubu0203.com.github.catcafe.common.controller.mapper.request;

import java.util.function.Function;
import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;

public interface UpdateRequestMapper<R, I extends ServiceInput> extends Function<R, I> {

}
