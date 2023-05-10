package cart.member.controller.dto;

import cart.member.domain.Member;

public class MemberResponse {

    private long id;
    private String email;
    private String password;

    private MemberResponse(long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getId(), member.getEmail(), member.getPassword());
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

}
