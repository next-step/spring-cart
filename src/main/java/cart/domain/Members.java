package cart.domain;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

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

    public void remove(Member member) {
        if (!memberContainer.containsKey(member.getId())) {
            throw new NoSuchElementException();
        }
        memberContainer.remove(member.getId());
    }

    public Optional<Member> findById(Long id) {
        if (!memberContainer.containsKey(id)) {
            return Optional.empty();
        }
        return Optional.of(memberContainer.get(id));
    }
}
