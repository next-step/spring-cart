package cart.user.domain;

import java.util.Objects;

public class User {
    private final UserEmail email;
    private final UserPassword password;

    public User(String email, String password) {
        this(new UserEmail(email), new UserPassword(password));
    }

    public User(UserEmail email, UserPassword password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}
