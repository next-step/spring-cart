package cart.service;

import cart.dao.MemberDAO;
import cart.model.Member;
import cart.model.Members;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private static final Logger logger = LogManager.getLogger(MemberService.class);
    private final MemberDAO memberDAO;

    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public Members memberList() {
        return memberDAO.list();
    }

    public int insert(Member member) {
        return memberDAO.insertMember(member);
    }

    public Member member(int id) {
        return memberDAO.getMember(id);
    }

    public int update(Member member) {
        return memberDAO.updateMember(member);
    }

    public int delete(int id) {
        return memberDAO.deleteMember(id);
    }
}
