package cart.domain;

public class Member {

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private final String email;
    private final String password;


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
