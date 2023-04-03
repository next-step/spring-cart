package cart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cart.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member save(Member product);

    Optional<Member> findById(Long id);

    List<Member> findAll();

    void deleteById(Long id);

}
