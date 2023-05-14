package cart.auth;

import cart.member.domain.dto.MemberDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthPrincipal {
    private Long memberId;
    private String email;

    public static AuthPrincipal from(MemberDto memberDto) {
        return new AuthPrincipal(memberDto.getId(), memberDto.getEmail());
    }
}
