package cart.infra.auth;

import cart.api.dto.AuthInfo;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BasicAuthorizationExtractorTest {

    private BasicAuthorizationExtractor basicAuthorizationExtractor = new BasicAuthorizationExtractor();

    @Test
    void extract() {
        String original = "a@a.com:password1";
        byte[] encodedBytes = Base64.encodeBase64(original.getBytes());
        String targetString = "basic" + new String(encodedBytes);

        AuthInfo extracted = basicAuthorizationExtractor.extract(targetString);

        assertThat(extracted.getEmail()).isEqualTo("a@a.com");
        assertThat(extracted.getPassword()).isEqualTo("password1");
    }
}
