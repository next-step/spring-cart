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
    private final Map<Long, MemberEntity> members = new HashMap<>();
    private final AtomicLong incrementId = new AtomicLong(1);

    public Long save(MemberEntity memberEntity) {
        Long id = incrementId.getAndIncrement();
        memberEntity.setId(id);
        this.members.put(id, memberEntity);
        return id;
    }

    public MemberEntity findByEmail(String email) {
        return this.members.values().stream()
                .filter(memberEntity -> memberEntity.getEmail().equals(email))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("이메일로 가입된 회원이 존재하지 않습니다. email:" + email));
    }

    public List<MemberEntity> findAll() {
        return new ArrayList<>(members.values());
    }
}
