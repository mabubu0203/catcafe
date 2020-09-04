package mabubu0203.com.github.catcafe.common.controller.mapper.request;

import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;

import java.util.Optional;
import java.util.function.Supplier;

public interface FindRequestMapper<I extends ServiceInput> extends Supplier<Optional<I>> {
}
