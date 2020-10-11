package mabubu0203.com.github.catcafe.common.controller.mapper.response;

import mabubu0203.com.github.catcafe.common.service.model.ServiceOutput;

import java.util.function.Function;

public interface DeleteResponseMapper<O extends ServiceOutput, Boolean> extends Function<O, Boolean> {
}
