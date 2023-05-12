package cart.member.application;

import cart.member.controller.dto.MemberRequest;
import cart.member.controller.dto.MembersResponse;
import cart.member.domain.MemberRepository;
import cart.member.domain.Members;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MembersResponse findAllMembers() {
        Members members = memberRepository.findAll();

        return MembersResponse.of(members);
    }

    public void createMember(MemberRequest request) {
        memberRepository.save(request.toMember());
    }

}
