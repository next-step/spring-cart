package cart.business;

import cart.data.MemberRepository;
import cart.presentation.dto.ViewMember;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<ViewMember> getMembers() {
        return memberRepository.getMembers()
                .stream()
                .map(ViewMember::new)
                .collect(Collectors.toList());
    }
}
