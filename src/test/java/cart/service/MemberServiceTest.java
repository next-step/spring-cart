package cart.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import cart.domain.Member;
import cart.repository.MemberRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = "classpath:data.sql")
@SpringBootTest
public class MemberServiceTest {

  public static final Member USER_1 = Member.builder()
      .id(1L)
      .email("a@a.com")
      .password("passwordA")
      .build();

  public static final Member USER_2 = Member.builder()
      .id(2L)
      .email("b@b.com")
      .password("passwordB")
      .build();

  @Autowired
  private MemberService memberService;
  @Autowired
  private MemberRepository memberRepository;

  @DisplayName("모든 멤버를 조회한다.")
  @Test
  void getAllMembers() {
    memberRepository.insert(USER_1);
    memberRepository.insert(USER_2);

    List<Member> users = memberService.findAll();

    assertThat(users).flatExtracting(Member::getEmail)
        .containsExactly("a@a.com", "b@b.com");
    assertThat(users).flatExtracting(Member::getPassword)
        .containsExactly("passwordA", "passwordB");
  }

  @DisplayName("로그인에 성공한다.")
  @Test
  void login() {
    Member insertedUser = memberRepository.insert(USER_1);

    Member loginUser = memberService.login("a@a.com", "passwordA");

    assertThat(loginUser).isEqualTo(insertedUser);
    assertThat(loginUser.getEmail()).isEqualTo(insertedUser.getEmail());
    assertThat(loginUser.getPassword()).isEqualTo(insertedUser.getPassword());
  }

  @DisplayName("존재하지않는 이메일로 로그인한 경우 IllegalArgumentException 발생한다.")
  @Test
  void loginWithWrongEmail() {
    Member insertedUser = memberRepository.insert(USER_1);

    assertThatThrownBy(() -> memberService.login("wrong@mail.com", insertedUser.getPassword()))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @DisplayName("비밀번호가 틀린 경우 IllegalArgumentException 발생한다.")
  @Test
  void loginWithWrongPassword() {
    Member insertedUser = memberRepository.insert(USER_1);

    assertThatThrownBy(() -> memberService.login(insertedUser.getEmail(), "passwordB"))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
