package cart.Service;


import cart.dao.MemberDAO;
import cart.domain.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private MemberDAO memberDAO;

    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }


    public List<Member> memberList(){
        List<Member> result = memberDAO.selectMembers();
        System.out.println(result.toString());
        return result;
    }


}
