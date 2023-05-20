package cart.infrastructure.security;

import cart.service.user.dto.UserDto;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BasicAuthorizationParserTest {

    @ValueSource(strings = {"BASIC", "Basic", "basic", " BASIC ", " Basic ", " basic "})
    @ParameterizedTest(name = "정상_authorization_header의_값을_파싱한다 - {0}")
    void 정상_authorization_header의_값을_파싱한다(String authorizationType) {
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
    @ParameterizedTest(name = "비정상_authorization_header의_값을_파싱한다_잘못된_값이_주어진_경우 - {0}")
    void 비정상_authorization_header의_값을_파싱한다_잘못된_값이_주어진_경우(String authorizationValue) {
        // given
        String credential = new String(Base64.encodeBase64(authorizationValue.getBytes()));

        // when, then
        assertThatThrownBy(() -> BasicAuthorizationParser.parse("Basic " + credential))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
