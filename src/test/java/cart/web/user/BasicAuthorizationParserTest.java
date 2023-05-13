package cart.web.user;

import cart.web.user.dto.UserDto;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BasicAuthorizationParserTest {

    @Test
    void parse() {
        // given
        String authorizationValue = new String(Base64.encodeBase64("a@a.com:passwordA".getBytes()));

        // when
        UserDto userDto = BasicAuthorizationParser.parse("BASIC " + authorizationValue);

        // then
        assertThat(userDto.getEmail()).isEqualTo("a@a.com");
        assertThat(userDto.getPassword()).isEqualTo("passwordA");
    }
}
