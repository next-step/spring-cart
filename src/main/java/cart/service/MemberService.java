package cart.service;

import cart.dao.MemberDao;
import cart.domain.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private MemberDao memberDao;

    public MemberService(MemberDao membershipDao) {
        this.memberDao = membershipDao;
    }

    public List<Member> showMember(){
        List<Member> result = memberDao.selectMember();
        return result;
    }

    public boolean validMember(Member member) {
        return memberDao.isValidMember(member);
    }

}
