package mabubu0203.com.github.catcafe.api.controller.contact;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ContactCommandApi;
import org.openapitools.model.ContactCreate;
import org.openapitools.model.PostObject;
import org.openapitools.model.ValidationResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ContactCommandApiController implements ContactCommandApi {

    @Operation(
            tags = {"contact_command",},
            summary = "お問い合わせ登録API",
            description = "お問い合わせを1件登録する",
            operationId = "contactCreate",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PostObject.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @Override
    public Mono<ResponseEntity<PostObject>> contactCreate(
            String cats,
            @Valid Mono<ContactCreate> contactCreate,
            ServerWebExchange exchange) {
        return null;
    }

}