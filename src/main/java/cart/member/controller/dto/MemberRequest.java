package cart.member.controller.dto;

import cart.member.domain.Member;

public class MemberRequest {

    private String email;
    private String password;

    public Member toMember() {
        return Member.of(email, password);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
