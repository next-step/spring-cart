package cart.user.domain;

import java.util.Objects;

public class UserPassword {
    private final String value;

    public UserPassword(String value) {
        this.value = value;
        validateNullOrBlank(this.value);
    }

    private void validateNullOrBlank(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPassword that = (UserPassword) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
