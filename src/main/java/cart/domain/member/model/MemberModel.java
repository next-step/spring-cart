package cart.domain.member.model;

public class MemberModel {

    private Long id;
    private String email;
    private String password;

    public MemberModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void addId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
