package cart.domain.service;

import cart.api.dto.AuthInfo;
import cart.domain.entity.Member;
import cart.domain.exception.AuthorizationException;
import cart.infra.auth.AuthorizationExtractor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AuthService {
    private final MemberService memberService;
    private final AuthorizationExtractor<AuthInfo> authExtractor;
    public AuthService(MemberService memberService, AuthorizationExtractor<AuthInfo> authExtractor) {
        this.memberService = memberService;
        this.authExtractor = authExtractor;
    }

    public Member validateAndLogin(String authorization) {
        AuthInfo authInfo = authExtractor.extract(authorization);
        String email = authInfo.getEmail();
        String password = authInfo.getPassword();
        try {
            Member member = memberService.findMember(email, password);
            return member;
        } catch (NoSuchElementException e) {
            throw new AuthorizationException();
        }
    }
}
