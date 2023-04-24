package cart.service;

import cart.domain.Member;
import cart.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    public Member findMember(String principal) {
        return memberRepository.findMember(principal);
    }
    public boolean checkInvalidLogin(String principal, String credentials) {
        Member member = memberRepository.findMember(principal);
        return (member != null && member.checkPassword(credentials));
    }
}
