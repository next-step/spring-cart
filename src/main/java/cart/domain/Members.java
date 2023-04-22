package cart.domain;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class Members {

    private final Map<Long, Member> memberContainer = new HashMap<>();
    private final AtomicLong incrementKey = new AtomicLong(0L);

    public Member add(Member member) {
        if (member.getId() == null) {
            member.setId(incrementKey.addAndGet(1L));
        }
        memberContainer.put(member.getId(), member);
        return member;
    }

    public List<Member> getAll() {
        return memberContainer.values()
            .stream()
            .sorted((p1, p2) -> (int) (p1.getId() - p2.getId()))
            .collect(Collectors.toList());
    }

    public Optional<Member> findByEmail(String email) {
        return memberContainer.values().stream()
            .filter(member -> member.isEmail(email))
            .findAny();
    }
}
