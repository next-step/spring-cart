package cart.infra;

public interface AuthorizationExtractor<T> {
    String AUTHORIZATION = "Authorization";

    T extract(String authorization);
}