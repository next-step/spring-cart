package cart.infrastructure.security;

import cart.web.user.dto.UserDto;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BasicAuthorizationParserTest {

    @ValueSource(strings = {"BASIC", "Basic", "basic", " BASIC ", " Basic ", " basic "})
    @ParameterizedTest
    void parse_valid(String authorizationType) {
        // given
        String credential = new String(Base64.encodeBase64("a@a.com:passwordA".getBytes()));

        // when
        UserDto userDto = BasicAuthorizationParser.parse(authorizationType + " " + credential);

        // then
        assertThat(userDto.getEmail()).isEqualTo("a@a.com");
        assertThat(userDto.getPassword()).isEqualTo("passwordA");
    }

    @EmptySource
    @ValueSource(strings = {"abc123", "abc;123", "a;1", "가나다"})
    @ParameterizedTest
    void parse_invalid(String authorizationValue) {
        // given
        String credential = new String(Base64.encodeBase64(authorizationValue.getBytes()));

        // when, then
        assertThatThrownBy(() -> BasicAuthorizationParser.parse("Basic " + credential))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
