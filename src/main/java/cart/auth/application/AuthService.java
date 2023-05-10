package cart.auth.application;

import cart.exception.ErrorType;
import cart.exception.ServiceException;
import cart.member.domain.Member;
import cart.member.domain.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final MemberRepository memberRepository;

    public AuthService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findByEmail(String email) {
        Member member = memberRepository.fidByEmail(email)
                .orElseThrow(() -> new ServiceException(ErrorType.NOT_FOUND_MEMBER));
        return member;
    }

}
