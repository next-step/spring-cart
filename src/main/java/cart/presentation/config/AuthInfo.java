package cart.presentation.config;

public class AuthInfo {

    private final String email;
    private final String password;

    public AuthInfo(String credentials) {
        String[] split = credentials.split(":");
        this.email = split[0];
        this.password = split[1];
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
