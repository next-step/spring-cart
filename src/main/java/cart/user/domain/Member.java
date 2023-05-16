package cart.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long id;
    private String email;
    private String password;
    private LocalDateTime createAt;

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
