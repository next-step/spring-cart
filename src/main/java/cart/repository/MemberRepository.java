package cart.repository;

import cart.domain.MemberEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class MemberRepository {
    Map<Long, MemberEntity> members = new HashMap<>();
    private final AtomicLong incrementId = new AtomicLong(1);

    public Long save(MemberEntity memberEntity) {
        Long id = incrementId.getAndIncrement();
        memberEntity.setId(id);
        this.members.put(id, memberEntity);
        return id;
    }

    public List<MemberEntity> findAll() {
        return new ArrayList<>(members.values());
    }
}
