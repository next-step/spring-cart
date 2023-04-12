package cart.infra;

import javax.servlet.http.HttpServletRequest;

public interface AuthorizationExtractor<T> {

    T extract(HttpServletRequest request);
}
