package mabubu0203.com.github.catcafe.common.service;

import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;
import mabubu0203.com.github.catcafe.common.service.model.ServiceOutput;

import java.util.concurrent.CompletableFuture;

public interface ApplicationService<I extends ServiceInput, O extends ServiceOutput> {

    CompletableFuture<O> promise(I input);

}
