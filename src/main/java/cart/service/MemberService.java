package cart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cart.domain.Member;
import cart.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> getList() {
        return memberRepository.findAll();
    }
}
