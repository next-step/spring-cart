package cart.member.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthInfo {

    private String email;

    private String password;
}
