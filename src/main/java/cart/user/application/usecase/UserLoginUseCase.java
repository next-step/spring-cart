package cart.user.application.usecase;

import cart.authenticate.AuthUserInformation;

public interface UserLoginUseCase {
    AuthUserInformation login(String email, String password);
}
