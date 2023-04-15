package cart.service;

import cart.controller.response.MemberResponse;
import cart.domain.Member;
import cart.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResponse> findAllMembers() {
        return memberRepository.findAll().stream().map(member -> new MemberResponse(member.getEmail(), member.getPassword())).collect(Collectors.toList());
    }

    public Member find(String email, String password) {
        return memberRepository.find(email, password);

    }

    public void save(Member member) {
        memberRepository.save(member);
    }
}
