package cart.controller.response;

public class MemberResponse {
    private final String email;
    private final String password;

    public MemberResponse(String email, String password) {
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
