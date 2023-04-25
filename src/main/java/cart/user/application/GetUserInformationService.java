package cart.user.application;

import cart.user.application.dto.UserInformation;
import cart.user.application.usecase.GetUserInformationUseCase;
import cart.user.domain.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetUserInformationService implements GetUserInformationUseCase {

    private final UserRepository userRepository;

    public GetUserInformationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserInformation> getUserInformations() {
        return userRepository.findAll()
                .stream()
                .map(UserInformation::fromUserEntity)
                .collect(Collectors.toList());
    }
}
