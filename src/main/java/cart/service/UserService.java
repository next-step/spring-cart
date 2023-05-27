package cart.service;

import cart.config.data.AuthInfo;
import cart.controller.dto.response.UserResponse;
import cart.controller.dto.response.UsersResponse;
import cart.domain.User;
import cart.exception.JwpCartApplicationException;
import cart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import static cart.exception.ErrorCode.INVALID_PASSWORD_INFORMATION;
import static cart.exception.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UsersResponse findAll() {
        List<UserResponse> responses = userRepository.findAll().stream().map(UserResponse::of).collect(Collectors.toList());
        return UsersResponse.of(responses);
    }

    public User login(AuthInfo authInfo) {
        User user = userRepository.findByEmail(authInfo.getEmail()).orElseThrow(() -> new JwpCartApplicationException(USER_NOT_FOUND));
        if (!user.checkPassword(authInfo.getPassword())) {
            throw new JwpCartApplicationException(INVALID_PASSWORD_INFORMATION);
        }
        return user;
    }
}
