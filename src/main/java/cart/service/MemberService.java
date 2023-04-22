package cart.service;

import cart.domain.Members;
import cart.value.MembersResponse;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final Members members;

    public MemberService(Members members) {
        this.members = members;
    }

    public MembersResponse findAllMembers() {
        return MembersResponse.from(members.findAll());
    }
}
