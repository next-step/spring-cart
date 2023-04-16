package cart.controller.response;

import cart.domain.Member;

public class MemberResponse {
    private final Long id;
    private final String email;
    private final String password;

    public MemberResponse(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static MemberResponse extract(Member member) {
        return new MemberResponse(member.getId(), member.getEmail(), member.getPassword());
    }
}
