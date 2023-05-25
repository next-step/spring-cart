package cart.controller.dto.response;

import cart.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String email;
    private String password;

    public static UserResponse of(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getPassword());
    }
}
