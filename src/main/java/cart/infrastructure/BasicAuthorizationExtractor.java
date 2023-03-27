package cart.infrastructure;

import cart.dto.AuthInfo;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;

public class BasicAuthorizationExtractor implements AuthorizationExtractor<AuthInfo> {

    private static final String BASIC_TYPE = "Basic";
    private static final String DELIMITER = ":";

    private static final Logger logger = LogManager.getLogger(BasicAuthorizationExtractor.class);

    @Override
    public AuthInfo extract(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION);

        if (header == null) {
            return null;
        }

        if ((header.toLowerCase().startsWith(BASIC_TYPE.toLowerCase()))) {
            String authHeaderValue = header.substring(BASIC_TYPE.length()).trim();
            byte[] decodedBytes = Base64.decodeBase64(authHeaderValue);
            String decodedString = new String(decodedBytes);

            String[] credentials = decodedString.split(DELIMITER);
            String email = credentials[0];
            String password = credentials[1];

            logger.debug("extract email -> ", email);
            logger.debug("extract password -> ", password);

            return new AuthInfo(email, password);
        }

        return null;
    }


}
