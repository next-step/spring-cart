package cart.service;

import cart.domain.Cart;
import cart.domain.Member;
import cart.exception.AuthorizationException;
import cart.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;

    public boolean checkInvalidLogin(Member input_member) {
        Member member = memberRepository.findMember(input_member.getEmail());
        return (member != null && member.equals(input_member));
    }

    public Cart toCart(Member request_member, Long productId) {
        if (!checkInvalidLogin(request_member)) {
            throw new AuthorizationException();
        }
        Member member = memberRepository.findMember(request_member.getEmail());
        return new Cart(member.getId(), productId);
    }

    public Member toMember(Member request_member) {
        if (!checkInvalidLogin(request_member)) {
            throw new AuthorizationException();
        }
        return memberRepository.findMember(request_member.getEmail());
    }
}
