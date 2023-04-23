package cart.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class Members {

    private final List<Member> memberContainer = new ArrayList<>();
    private final AtomicLong incrementKey = new AtomicLong(0L);

    public Member add(Member member) {
        if (member.getId() == null) {
            member.setId(incrementKey.incrementAndGet());
            memberContainer.add(member);
            return member;
        }
        final Member saved = findById(member.getId()).orElseThrow();
        saved.update(member);
        return saved;
    }

    public List<Member> getAll() {
        return memberContainer.stream()
            .sorted((p1, p2) -> (int) (p1.getId() - p2.getId()))
            .collect(Collectors.toList());
    }

    public Optional<Member> findByEmail(String email) {
        return memberContainer.stream()
            .filter(member -> member.isEmail(email))
            .findAny();
    }

    public Optional<Member> findById(Long id) {
        return memberContainer.stream()
            .filter(member -> member.getId().equals(id))
            .findAny();
    }
}
