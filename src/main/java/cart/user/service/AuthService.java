package cart.user.service;

import cart.infra.AuthorizationExtractor;
import cart.user.domain.Member;
import cart.user.dto.response.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthorizationExtractor<MemberDetails> authorizationExtractor;
    private final MemberService memberService;

    public Member validate(@RequestHeader String authorization) {
        MemberDetails memberDetails = authorizationExtractor.extract(authorization);
        return memberService.findByEmailAndPassword(memberDetails.getEmail(), memberDetails.getPassword());
    }
}
