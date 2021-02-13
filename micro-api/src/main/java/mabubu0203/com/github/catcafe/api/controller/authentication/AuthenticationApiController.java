package mabubu0203.com.github.catcafe.api.controller.authentication;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.authentication.helper.request.XApiKeyGenerateRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.authentication.helper.response.XApiKeyGenerateResponseMapper;
import mabubu0203.com.github.catcafe.api.controller.authentication.service.XApiKeyGenerateService;
import org.openapitools.api.AuthenticationApi;
import org.openapitools.model.ValidationErrorResponse;
import org.openapitools.model.XApiKeyGenerate;
import org.openapitools.model.XApiKeyGenerateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AuthenticationApiController implements AuthenticationApi {

  private final XApiKeyGenerateService xApiKeyGenerateService;

  @Operation(
      tags = {"authentication",},
      summary = "X-API-KEY生成API",
      description = "X-API-KEYを生成する",
      operationId = "xApiKeyGenerate",
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = XApiKeyGenerateResponse.class))),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
      }
  )
  @Override
  public Mono<ResponseEntity<XApiKeyGenerateResponse>> xApiKeyGenerate(
      @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
      @Valid Mono<XApiKeyGenerate> xapiKeyGenerate,
      ServerWebExchange exchange) {
    return xapiKeyGenerate
        .map(new XApiKeyGenerateRequestMapper(cats, this.getClientIp(exchange)))
        .map(this.xApiKeyGenerateService::promise)
        .flatMap(Mono::fromCompletionStage)
        .map(new XApiKeyGenerateResponseMapper())
        .map(ResponseEntity.status(HttpStatus.OK)::body);
  }

  private String getClientIp(ServerWebExchange exchange) {
    return
        Optional.ofNullable(exchange)
            .map(ServerWebExchange::getRequest)
            .map(ServerHttpRequest::getRemoteAddress)
            .map(InetSocketAddress::getAddress)
            .map(InetAddress::getHostAddress)
            .orElse("");
  }

}
