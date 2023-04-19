package cart.domain.member;

import cart.domain.member.model.MemberModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MemberRepository {

    private static final List<MemberModel> MEMBERS = new ArrayList<>();
    private static Long id = 0L;

    public Long save(MemberModel memberModel) {
        memberModel.addId(++id);
        MEMBERS.add(memberModel);
        return id;
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
