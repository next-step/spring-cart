package cart.member.domain;

import java.util.Optional;

public interface MemberRepository {

    Members findAll();

    Optional<Member> fidByEmail(String email);

    void save(Member member);

}
