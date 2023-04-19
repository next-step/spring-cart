package cart.domain.member;

import cart.domain.member.model.MemberModel;
import cart.domain.member.query.CheckMemberQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberModel> memberList() {
        return memberRepository.findAll();
    }

    public boolean checkMember(CheckMemberQuery checkMemberQuery) {
        return memberRepository.findByEmail(checkMemberQuery.getEmail())
            .map(memberModel -> memberModel.getPassword().equals(checkMemberQuery.getPassword()))
            .orElse(false);
    }
}
