package mabubu0203.com.github.catcafe.domain.value;

import java.util.Arrays;
import java.util.Optional;

/**
 * URL
 */
public record HttpUrl(String value) {

  public boolean isEmpty() {
    return Optional.ofNullable(value)
        .map(String::isEmpty)
        .orElse(true);
  }

  public String scheme() {
    return this.isEmpty() ?
        "" : Arrays.stream(value.split("/")).findFirst().orElse("");
  }

  public String host() {
    return this.isEmpty() ?
        "" : Arrays.asList(value.split("/")).get(1);
  }

}
