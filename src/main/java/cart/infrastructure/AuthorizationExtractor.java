package cart.infrastructure;

import java.nio.file.AccessDeniedException;
import javax.servlet.http.HttpServletRequest;

public interface AuthorizationExtractor<T> {

    T extract(String header) throws AccessDeniedException;
}
