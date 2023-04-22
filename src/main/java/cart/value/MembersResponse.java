package cart.value;

import cart.domain.Member;

import java.util.List;
import java.util.stream.Collectors;

public class MembersResponse {
    private final List<MemberResponse> members;

    private MembersResponse(List<MemberResponse> members) {
        this.members = members;
    }

    public static MembersResponse from(List<Member> members) {
        return new MembersResponse(members.stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList()));
    }

    public List<MemberResponse> getMembers() {
        return members;
    }
}
