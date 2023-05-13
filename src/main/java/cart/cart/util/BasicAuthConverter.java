package cart.cart.util;

import java.util.Base64;

public class BasicAuthConverter {
    public static AuthInfo convert(String basicAuthLiteral) {
        String authLiteral = basicAuthLiteral.replace("Basic ", "");
        String decodedString
                = new String(Base64.getDecoder().decode(authLiteral.getBytes()));


        String[] authArray = decodedString.split(":");
        return new AuthInfo(authArray[0], authArray[1]);
    }
}
