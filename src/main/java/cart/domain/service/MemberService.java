package cart.domain.service;

import cart.domain.entity.Member;
import cart.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Collection<Member> getAll() {
        return memberRepository.findAll();
    }

    public Member findMember(String email, String password) {
        return memberRepository.findByEmailAndPassword(email, password);
    }
}
