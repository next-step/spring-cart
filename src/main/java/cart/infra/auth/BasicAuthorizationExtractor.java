package cart.infra.auth;

import cart.api.dto.AuthInfo;
import cart.domain.exception.AuthorizationException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service
public class BasicAuthorizationExtractor implements AuthorizationExtractor<AuthInfo> {
    private static final String BASIC_TYPE = "Basic";
    private static final String DELIMITER = ":";

    @Override
    public AuthInfo extract(String authorization) {
        if (authorization == null) {
            throw new AuthorizationException("인증 정보가 없습니다.");
        }

        if (authorization.toLowerCase().startsWith(BASIC_TYPE.toLowerCase())) {
            String authHeaderValue = authorization.substring(BASIC_TYPE.length()).trim();
            byte[] decodedBytes = Base64.decodeBase64(authHeaderValue);
            String decodedString = new String(decodedBytes);

            String[] credentials = decodedString.split(DELIMITER);
            String email = credentials[0];
            String password = credentials[1];

            return new AuthInfo(email, password);
        }
        throw new AuthorizationException("인증 정보가 올바르지 않습니다.");
    }
}
