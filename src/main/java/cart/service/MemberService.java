package cart.service;

import cart.controller.response.MemberResponse;
import cart.domain.Member;
import cart.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResponse> findAllMembers() {
        return memberRepository.findAll().stream()
                .map(MemberResponse::extract)
                .collect(Collectors.toList());
    }

    public MemberResponse find(String email, String password) {
        Member member = memberRepository.find(email, password);
        return MemberResponse.extract(member);
    }
}
