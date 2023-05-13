package cart.domain.repository;

import cart.domain.entity.Member;

import java.util.Collection;

public interface MemberRepository {
    Collection<Member> findAll();
    Member findByEmailAndPassword(String email, String password);
}
