package cart.data;

import cart.data.entity.CartMember;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    List<CartMember> getMembers();

    Optional<CartMember> getMember(String email, String password);
}
