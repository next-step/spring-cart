package cart.service;

import cart.domain.Member;
import cart.domain.Members;
import cart.exception.AuthorizationException;
import cart.value.MembersResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final Members members;

    public MemberService(Members members) {
        this.members = members;
    }

    public MembersResponse findAllMembers() {
        return MembersResponse.from(members.findAll());
    }

    public Long checkAndGetId(List<String> credentials) {
        String email = credentials.get(0);
        String password = credentials.get(1);

        Member member = members.findByEmail(email);
        if (!member.hasPassword(password)) {
            throw new AuthorizationException("비밀번호가 일치하지 않습니다.");
        }
        return member.getId();
    }
}
