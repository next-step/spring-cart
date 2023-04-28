package cart.user.web.response;

import cart.user.application.dto.UserInformation;

import java.util.List;
import java.util.stream.Collectors;

public class SettingPageMemberResponse {
    private final String email;
    private final String password;

    public SettingPageMemberResponse(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static List<SettingPageMemberResponse> ofUserInformations(List<UserInformation> userInformations) {
        return userInformations.stream()
                .map(SettingPageMemberResponse::fromUserInformation)
                .collect(Collectors.toList());
    }

    private static SettingPageMemberResponse fromUserInformation(UserInformation userInformation) {
        return new SettingPageMemberResponse(
                userInformation.getEmail(),
                userInformation.getPassword()
        );
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
