package cart.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(of = "id")
@Getter
@AllArgsConstructor
@Builder
public class User {

  private Long id;
  private String email;
  private String password;
}
