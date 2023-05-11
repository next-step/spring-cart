package cart.member.domain;

import java.util.List;

public interface MemberRepository {

    List<Member> findAll();
}
