package cart.service.user;

import cart.domain.user.User;
import cart.infrastructure.dao.UsersDao;
import cart.infrastructure.security.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UsersDao usersDao;

    public User login(String email, String password) {
        User foundUser = usersDao.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일 입니다."));

        if (!foundUser.getPassword().equals(password)) {
            throw new AuthenticationException("비밀번호가 유효하지 않습니다.");
        }

        return foundUser;
    }

}
