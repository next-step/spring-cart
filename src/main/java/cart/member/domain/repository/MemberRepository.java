package cart.member.domain.repository;

import cart.member.domain.entity.Member;

import java.util.List;

public interface MemberRepository {
    List<Member> findAll();

    Member findById(Long id);
}
