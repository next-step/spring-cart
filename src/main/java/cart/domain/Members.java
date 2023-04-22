package cart.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
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
}
