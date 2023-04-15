package cart.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Member {
    private final long id;
    private final String email;
    private final String password;

    public Member(long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public static Map<String, String> getInsertParameter(Member member) {
            Map<String, String> parameters = new HashMap<>(2);
            parameters.put("email", member.getEmail());
            parameters.put("password", member.getPassword());
            return parameters;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return id == member.id && Objects.equals(email, member.email) && Objects.equals(password, member.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }
}
