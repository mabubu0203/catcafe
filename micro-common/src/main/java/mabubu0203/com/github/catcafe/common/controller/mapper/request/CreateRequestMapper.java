package mabubu0203.com.github.catcafe.common.controller.mapper.request;

import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;

import java.util.function.Function;

public interface CreateRequestMapper<R, I extends ServiceInput> extends Function<R, I> {
}
