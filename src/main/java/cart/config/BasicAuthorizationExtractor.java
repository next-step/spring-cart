package cart.config;

import cart.domain.Member;
import org.apache.tomcat.util.codec.binary.Base64;


import javax.servlet.http.HttpServletRequest;

public class BasicAuthorizationExtractor implements AuthorizationExtractor<Member> {
    private static final String BASIC_TYPE = "Basic";
    private static final String DELIMITER = ":";

    @Override
    public Member extract(HttpServletRequest request) throws Exception {
        String header = request.getHeader(AUTHORIZATION);

        if (header == null) {
            throw new Exception("인증에 실패하였습니다.");
        }

        if ((header.toLowerCase().startsWith(BASIC_TYPE.toLowerCase()))) {
            String authHeaderValue = header.substring(BASIC_TYPE.length()).trim();
            byte[] decodedBytes = Base64.decodeBase64(authHeaderValue);
            String decodedString = new String(decodedBytes);

            String[] credentials = decodedString.split(DELIMITER);
            String email = credentials[0];
            String password = credentials[1];

            return new Member(email, password);
        }

        return null;
    }


}
