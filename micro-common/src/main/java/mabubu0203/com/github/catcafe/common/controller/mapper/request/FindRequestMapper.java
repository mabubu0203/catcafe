package mabubu0203.com.github.catcafe.common.controller.mapper.request;

import java.util.function.Supplier;
import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;
import reactor.core.publisher.Mono;

public interface FindRequestMapper<I extends ServiceInput> extends Supplier<Mono<I>> {

}
