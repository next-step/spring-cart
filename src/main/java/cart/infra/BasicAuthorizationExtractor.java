package cart.infra;

import cart.member.model.AuthInfo;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.servlet.http.HttpServletRequest;

public class BasicAuthorizationExtractor implements AuthorizationExtractor<AuthInfo> {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BASIC_TYPE = "Basic";
    private static final String DELIMITER = ":";


    @Override
    public AuthInfo extract(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION);

        if (header == null) {
            throw new AuthorizationException("인증 값 없음");
        }

        if ((header.toLowerCase().startsWith(BASIC_TYPE.toLowerCase()))) {
            String authHeaderValue = header.substring(BASIC_TYPE.length()).trim();
            byte[] decodedBytes = Base64.decodeBase64(authHeaderValue);
            String decodedString = new String(decodedBytes);

            String[] credentials = decodedString.split(DELIMITER);
            String email = credentials[0];
            String password = credentials[1];

            return new AuthInfo(email, password);
        }

        return null;
    }
}
