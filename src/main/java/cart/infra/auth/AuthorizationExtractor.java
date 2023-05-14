package cart.infra.auth;

@FunctionalInterface
public interface AuthorizationExtractor<T> {
    String AUTHORIZATION = "Authorization";

    T extract(String authorization);
}
