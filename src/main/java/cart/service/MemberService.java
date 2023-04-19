package cart.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cart.domain.Member;
import cart.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<Member> getList() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member getMember(String email, String password) {
        return memberRepository.findByEmailAndPassword(email, password);
    }

    @Transactional(readOnly = true)
    public boolean hasMember(String email, String password) {
        return getMember(email, password) != null;
    }
}
