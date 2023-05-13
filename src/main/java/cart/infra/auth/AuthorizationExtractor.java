package cart.infra.auth;

public interface AuthorizationExtractor<T> {
    String AUTHORIZATION = "Authorization";

    T extract(String authorization);
}
