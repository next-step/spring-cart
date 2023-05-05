package cart.config;

import cart.domain.AuthInfo;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AuthExtractor {
    public static AuthInfo extractAuthInfo(String authorizationValue) {
        String encodingCredentials = authorizationValue.substring("Basic".length()).trim();
        byte[] credentialsDecoded = Base64.getDecoder().decode(encodingCredentials);
        String credentials = new String(credentialsDecoded, StandardCharsets.UTF_8);
        String[] memberInfo = credentials.split(":");
        return new AuthInfo(memberInfo[0], memberInfo[1]);
    }
}
