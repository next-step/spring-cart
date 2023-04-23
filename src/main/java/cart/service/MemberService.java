package cart.service;

import cart.domain.MemberEntity;
import cart.presentation.dto.MemberDetailResponse;
import cart.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberDetailResponse> allMembers() {
        return memberRepository.findAll().stream()
                .map(MemberDetailResponse::from)
                .collect(Collectors.toList());
    }

    public MemberEntity findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}
