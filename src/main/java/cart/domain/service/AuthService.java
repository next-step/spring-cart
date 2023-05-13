package cart.domain.service;

import cart.domain.entity.Member;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AuthService {
    private final MemberService memberService;
    public AuthService(MemberService memberService) {
        this.memberService = memberService;
    }

    public boolean checkInvalidLogin(String email, String password) {
        try {
            Member member = memberService.findMember(email, password);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
