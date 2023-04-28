package cart.user.application.usecase;

import cart.user.application.dto.UserInformation;

import java.util.List;

public interface GetUserInformationUseCase {
    List<UserInformation> getUserInformations();
}
