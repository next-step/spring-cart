package cart.infra;

import cart.enums.exceptions.ErrorCode;
import cart.exceptions.MemberNotFoundException;
import cart.user.dto.response.MemberDetails;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class BasicAuthorizationExtractor implements AuthorizationExtractor<MemberDetails> {
    private static final String BASIC_TYPE = "Basic";
    private static final String DELIMITER = ":";

    @Override
    public MemberDetails extract(String authorization) {

        if (authorization == null) {
            throw new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER);
        }

        if ((authorization.toLowerCase().startsWith(BASIC_TYPE.toLowerCase()))) {
            String authHeaderValue = authorization.substring(BASIC_TYPE.length()).trim();
            byte[] decodedBytes = Base64.decodeBase64(authHeaderValue);
            String decodedString = new String(decodedBytes);

            String[] credentials = decodedString.split(DELIMITER);
            String email = credentials[0];
            String password = credentials[1];

            return new MemberDetails(email, password);
        }

        throw new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER);
    }
}
