package cart.presentation.dto;

import cart.domain.MemberEntity;

public class MemberDetailResponse {
    private final String email;
    private final String password;

    public MemberDetailResponse(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static MemberDetailResponse from(MemberEntity member) {
        return new MemberDetailResponse(
                member.getEmail(),
                member.getPassword()
        );
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
