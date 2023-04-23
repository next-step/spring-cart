package cart.dto;

public class MemberToken {

    private final String email;
    private final String password;

    public MemberToken(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
