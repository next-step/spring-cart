package cart.domain;

import cart.exception.MemberException;

import java.util.Objects;

public class Member {

    private Long id;
    private String email;
    private String password;

    public Member() {
    }

    public Member(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Member(String email, String password) {
        this(null, email, password);
    }

    public void authorize(String email, String password) {
        if (!this.email.equals(email) || !this.password.equals(password)) {
            throw new MemberException("인증 실패");
        }
    }

    public boolean isEmail(String email) {
        return this.email.equals(email);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Member product = (Member) o;
        return Objects.equals(id, product.id) && Objects.equals(email, product.email)
            && Objects.equals(password, product.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }
}
