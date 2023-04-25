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

    public UserEmail getEmail() {
        return email;
    }

    public String getEmailValue() {
        return email.getValue();
    }

    public String getPasswordValue() {
        return password.getValue();
    }

    public void login(UserPassword password) {
        if (this.password.equals(password)) {
            return;
        }
        throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
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
