package mabubu0203.com.github.catcafe.common.controller.mapper.request;

import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

public interface SearchRequestMapper<I extends ServiceInput> extends Supplier<Mono<I>> {
}
