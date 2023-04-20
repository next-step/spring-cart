package cart.dto;

import cart.domain.Member;

import java.util.List;
import java.util.stream.Collectors;

public class MemberResponse {

    private Long id;
    private String email;
    private String password;


    public MemberResponse() {
    }

    public MemberResponse(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getId(), member.getEmail(), member.getPassword());
    }

    public static List<MemberResponse> listOf(List<Member> members) {
        return members.stream()
            .map(MemberResponse::of)
            .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
