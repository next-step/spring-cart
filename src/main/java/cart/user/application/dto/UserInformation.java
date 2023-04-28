package cart.user.application.dto;

import cart.user.domain.User;
import cart.user.domain.UserEntity;

public class UserInformation {
    private final String email;
    private final String password;

    public UserInformation(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static UserInformation fromUser(User user) {
        return new UserInformation(
                user.getEmailValue(),
                user.getPasswordValue()
        );
    }

    public static UserInformation fromUserEntity(UserEntity userEntity) {
        return new UserInformation(
                userEntity.getEmailValue(),
                userEntity.getPassworldValue()
        );
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
