package cart.infrastructure.security;

import cart.web.user.dto.UserDto;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class BasicAuthorizationParserTest {

    @ValueSource(strings = {"BASIC", "Basic", "basic", " BASIC ", " Basic ", " basic "})
    @ParameterizedTest
    void parse(String authorizationType) {
        // given
        String credential = new String(Base64.encodeBase64("a@a.com:passwordA".getBytes()));

        // when
        UserDto userDto = BasicAuthorizationParser.parse(authorizationType + " " + credential);

        // then
        assertThat(userDto.getEmail()).isEqualTo("a@a.com");
        assertThat(userDto.getPassword()).isEqualTo("passwordA");
    }

}
