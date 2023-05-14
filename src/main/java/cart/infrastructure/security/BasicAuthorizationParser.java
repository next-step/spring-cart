package cart.infrastructure.security;

import cart.web.user.dto.UserDto;
import org.apache.tomcat.util.codec.binary.Base64;

public class BasicAuthorizationParser {

    private static final String TYPE = "BASIC";
    private static final String SEPARATOR = ":";

    public static UserDto parse(String authorizationHeader) {
        String authorizationValue = authorizationHeader.split(TYPE)[1].trim();
        String decodedValue = new String(Base64.decodeBase64(authorizationValue));

        String[] splitDecodedValue = decodedValue.split(SEPARATOR);
        String email = splitDecodedValue[0];
        String password = splitDecodedValue[1];

        return new UserDto(email, password);
    }

}
