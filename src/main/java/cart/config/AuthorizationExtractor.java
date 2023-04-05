package cart.config;

import javax.servlet.http.HttpServletRequest;

public interface AuthorizationExtractor<T> {

    T extract(HttpServletRequest request) throws Exception;
}
