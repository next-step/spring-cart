package cart.auth;

public class AuthDto {
    private final Long id;

    public AuthDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
