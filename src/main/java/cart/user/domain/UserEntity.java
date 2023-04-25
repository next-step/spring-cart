package cart.user.domain;

import java.util.Objects;

public class UserEntity {
    private final UserId id;
    private final User user;

    public UserEntity(UserId id, User user) {
        this.id = id;
        this.user = user;
    }

    public UserEntity update(User user) {
        return new UserEntity(id, user);
    }

    public UserId getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getEmailValue() {
        return user.getEmailValue();
    }

    public String getPassworldValue() {
        return user.getPasswordValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
