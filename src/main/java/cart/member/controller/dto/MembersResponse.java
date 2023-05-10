package cart.member.controller.dto;

import cart.member.domain.Members;

import java.util.List;
import java.util.stream.Collectors;

public class MembersResponse {

    private List<MemberResponse> members;

    private MembersResponse(List<MemberResponse> members) {
        this.members = members;
    }


    public static MembersResponse of(Members members) {
        List<MemberResponse> responses = members.getValue()
                .stream()
                .map(MemberResponse::of)
                .collect(Collectors.toList());

        return new MembersResponse(responses);
    }

    public List<MemberResponse> getMembers() {
        return members;
    }

}
