package cart.infra;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface AuthorizationExtractor<T> {
    String AUTHORIZATION = "Authorization";

    T extract(String authorization);
}
