package cart.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class UsersResponse {

    private List<UserResponse> userResponses;

    public static UsersResponse of (List<UserResponse> userResponses) {
        return new UsersResponse(userResponses);
    }
}
