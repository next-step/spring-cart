package cart.auth;

public class AuthData {
    private final String email;
    private final String password;

    public AuthData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "AuthData{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
