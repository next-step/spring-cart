package cart.domain.member.query;

public class CheckMemberQuery {

    private final String email;
    private final String password;

    public CheckMemberQuery(String email, String password) {
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
