package cart.presentation.util;

import cart.presentation.config.AuthInfo;
import org.apache.tomcat.util.codec.binary.Base64;

public class AuthHeaderParser {

    private AuthHeaderParser() {}

    public static AuthInfo parse(String authorizationHeader) {
        String decoded = decode(parseCredentials(authorizationHeader));
        return new AuthInfo(decoded);
    }

    private static String parseCredentials(String authorizationHeader) {
        return authorizationHeader.split(" ")[1];
    }

    private static String decode(String credentials) {
        return new String(Base64.decodeBase64(credentials));
    }
}
