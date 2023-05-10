package cart.member.domain.service;

import cart.member.domain.dto.MemberDto;
import cart.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<MemberDto> getAllMember() {
        return memberRepository.findAll().stream()
                .map(MemberDto::from)
                .collect(Collectors.toList());
    }
}
