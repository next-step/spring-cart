package cart.dto;

import cart.config.Login;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberDto {
  private final Login id;
  private final String email;
  private final String password;
}
