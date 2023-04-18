package cart.infra;

public interface AuthorizationExtractor<T> {
    T extract(String authorization);
}