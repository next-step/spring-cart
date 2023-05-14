package cart.data;

import cart.data.entity.CartMember;

import java.util.List;

public interface MemberRepository {
    List<CartMember> getMembers();
}
