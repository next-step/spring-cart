package cart.infra;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface AuthorizationExtractor<T> {
    String AUTHORIZATION = "Authorization";

    Optional<T> extract(HttpServletRequest request);
}
