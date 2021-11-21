package mabubu0203.com.github.catcafe.domain.value;

import mabubu0203.com.github.catcafe.domain.check.CheckPhoneNumber;

public record PhoneNumber(@CheckPhoneNumber String value) {

}
