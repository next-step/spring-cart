package cart.domain.member;

import cart.domain.member.model.MemberModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemberRepository {

    private static final List<MemberModel> MEMBERS = new ArrayList<>();
    private static final AtomicLong id = new AtomicLong(0);

    public Long save(MemberModel memberModel) {
        long memberId = id.incrementAndGet();
        memberModel.addId(memberId);
        MEMBERS.add(memberModel);
        return memberId;
    }

    public List<MemberModel> findAll() {
        return new ArrayList<>(MEMBERS);
    }

    public Optional<MemberModel> findByEmail(String email) {
        return MEMBERS.stream()
            .filter(memberModel -> memberModel.getEmail().equals(email))
            .findAny();
    }
}
