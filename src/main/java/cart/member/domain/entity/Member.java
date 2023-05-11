package cart.member.domain.entity;

import cart.member.domain.vo.MemberId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Member {
    private MemberId id;
    private String email;
    private String password;

    public Long getId() {
        return id.getId();
    }
}
