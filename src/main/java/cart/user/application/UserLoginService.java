package cart.user.application;

import cart.user.application.dto.UserInformation;
import cart.user.application.usecase.UserLoginUseCase;
import cart.user.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class UserLoginService implements UserLoginUseCase {

    private final UserRepository userRepository;

    public UserLoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserInformation login(String email, String password) {
        UserEmail userEmail = new UserEmail(email);
        UserPassword userPassword = new UserPassword(password);
        UserEntity userEntity = userRepository.findByEmail(userEmail);
        User user = userEntity.getUser();
        user.login(userPassword);
        return UserInformation.fromUser(user);
    }
}
