package cart.infrastructure.security;

import cart.service.user.dto.UserDto;
import org.apache.tomcat.util.codec.binary.Base64;

public class BasicAuthorizationParser {

    private static final int TYPE_LENGTH = 5;
    private static final String SEPARATOR = ":";

    public static UserDto parse(String authorizationHeader) {
        try {
            return parseHeader(authorizationHeader);
        } catch (RuntimeException exception) {
            throw new IllegalArgumentException("로그인 정보가 잘못되었습니다.");
        }
    }

    private static UserDto parseHeader(String authorizationHeader) {
        String authorizationValue = authorizationHeader.trim().substring(TYPE_LENGTH).trim();
        String decodedValue = new String(Base64.decodeBase64(authorizationValue));

        String[] splitDecodedValue = decodedValue.split(SEPARATOR);
        String email = splitDecodedValue[0];
        String password = splitDecodedValue[1];

        return new UserDto(email, password);
    }

}
