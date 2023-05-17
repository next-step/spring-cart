package cart.infra.auth;

@FunctionalInterface
public interface AuthorizationExtractor<T> {
    T extract(String authorization);
}
