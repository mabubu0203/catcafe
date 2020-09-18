package mabubu0203.com.github.catcafe.api.controller.cast.service.model.input;

import lombok.Data;
import lombok.experimental.Accessors;
import mabubu0203.com.github.catcafe.common.service.model.ServiceInput;

import java.util.List;

@Accessors(chain = true)
@Data
public class CastCatResisterServiceInput implements ServiceInput {

    private String name;
    private List<Integer> brother = null;
    private List<Integer> sister = null;

}
