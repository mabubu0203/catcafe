package mabubu0203.com.github.catcafe.api.controller.contact;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.ContactApi;
import org.openapitools.model.InlineObject4;
import org.openapitools.model.InlineResponse200;
import org.openapitools.model.InlineResponse2001;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContactApiController implements ContactApi {

    @Override
    public ResponseEntity<InlineResponse200> contactCreate(String cats, @Valid InlineObject4 inlineObject4) {
        return null;
    }

    @Override
    public ResponseEntity<InlineResponse2001> contactFind(String cats, Integer contactId) {
        return null;
    }

    @Override
    public ResponseEntity<List<InlineResponse2001>> contactSearch(String cats, @Valid List<Integer> store) {
        return null;
    }

}
