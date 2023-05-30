package cart.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(of = "id")
@Getter
@AllArgsConstructor
@Builder
public class Member {

  private final Long id;
  private final String email;
  private final String password;

  public void validatePassword(String password) {
    if (!this.password.equals(password)) {
      throw new IllegalArgumentException("비밀번호가 유효하지 않습니다.");
    }
  }
}
