package cart.domain.service;

import cart.api.dto.AuthInfo;
import cart.domain.entity.Member;
import cart.domain.exception.AuthorizationException;
import cart.domain.repository.MemberRepository;
import cart.infra.auth.AuthorizationExtractor;
import cart.testdouble.InMemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthServiceTest {

    private MemberRepository memberRepository = new InMemoryMemberRepository();
    private MemberService memberService = new MemberService(memberRepository);
    private AuthorizationExtractor<AuthInfo> authExtractor = (x) -> {
        return new AuthInfo("a@a.com", "password1");
    };
    private AuthService authService = new AuthService(memberService, authExtractor);

    @Test
    void validateAndLogin() {
        Member member1 = new Member(1L, "a@a.com", "password1");
        memberRepository.insert(member1);

        Member validated = authService.validateAndLogin("123ABC456def");

        assertThat(validated.getEmail()).isEqualTo("a@a.com");
        assertThat(validated.getPassword()).isEqualTo("password1");
    }

    @DisplayName("해당하는 회원을 찾지 못하면 예외가 발생한다.")
    @Test
    void validateAndLoginWithException() {
        Member member1 = new Member(1L, "b@b.com", "password2");
        memberRepository.insert(member1);


        assertThrows(AuthorizationException.class, () -> {
            authService.validateAndLogin("123ABC456def");
        });
    }
}
