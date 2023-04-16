package cart.service;

import cart.controller.response.MemberResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    void findAllMembers() {

        List<MemberResponse> expectList = List.of(
                new MemberResponse(1L, "test@naver.com", "password!!"),
                new MemberResponse(2L, "abc@test.com", "password@@"));

        List<MemberResponse> allMembers = memberService.findAllMembers();

        Assertions.assertThat(allMembers).hasSize(2);
        Assertions.assertThat(allMembers).containsExactlyElementsOf(expectList);
    }

    @Test
    void find() {
        MemberResponse memberResponse = memberService.find("test@naver.com", "password!!");
        Assertions.assertThat(memberResponse).isEqualTo(new MemberResponse(1L, "test@naver.com", "password!!"));
    }
}