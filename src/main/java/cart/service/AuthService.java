package cart.service;

import java.nio.file.AccessDeniedException;

import org.springframework.stereotype.Service;

import cart.domain.AuthInfo;
import cart.infra.AuthorizationExtractor;
import cart.infra.BasicAuthorizationExtractor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberService memberService;

    private AuthorizationExtractor<AuthInfo> basicAuthorizationExtractor = new BasicAuthorizationExtractor();

    public AuthInfo createAuth(String authorization) throws AccessDeniedException {
        AuthInfo authInfo = basicAuthorizationExtractor.extract(authorization);
        if (checkInvalidLogin(authInfo.getEmail(), authInfo.getPassword())) {
            throw new AuthorizationException();
        }
        return authInfo;
    }

    private boolean checkInvalidLogin(String email, String password) {
        return !memberService.hasMember(email, password);
    }
}
