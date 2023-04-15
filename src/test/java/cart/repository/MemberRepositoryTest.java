package cart.repository;

import cart.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void findAll() {
        List<Member> allProduct = memberRepository.findAll();
        List<Member> expectList = List.of(
                new Member(1, "test@naver.com", "password!!"),
                new Member(2, "abc@test.com", "password@@"));
        Assertions.assertThat(allProduct).containsExactly(expectList.toArray(Member[]::new));
    }

    @Test
    public void find() {
        Member member = memberRepository.find("test@naver.com", "password!!");

        Member expect = new Member(1, "test@naver.com", "password!!");
        Assertions.assertThat(member).isEqualTo(expect);
    }

    @Test
    public void findIllegalArgument() {
        Assertions.assertThatThrownBy(() -> memberRepository.find("none", "password!!"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 사용자입니다.");
    }
}