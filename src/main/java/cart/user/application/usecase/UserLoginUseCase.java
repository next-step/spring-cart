package cart.user.application.usecase;

import cart.user.application.dto.UserInformation;

public interface UserLoginUseCase {
    UserInformation login(String email, String password);
}
