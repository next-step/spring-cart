package cart.domain;

import cart.exception.NoSuchMemberException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class Members {
    private Long tmpId = 1L;
    private final List<Member> values = new ArrayList<>();

    public Member save(Member member) {
        member.setId(tmpId++);
        values.add(member);
        return member;
    }

    public List<Member> findAll() {
        return values;
    }

    public Member findByEmail(String email) {
        return values.stream()
                .filter(it -> Objects.equals(it.getEmail(), email))
                .findAny()
                .orElseThrow(NoSuchMemberException::new);
    }
}
