package cart.domain.service;

import cart.domain.entity.Member;
import cart.domain.repository.MemberRepository;
import cart.testdouble.InMemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    private MemberRepository memberRepository = new InMemoryMemberRepository();
    private MemberService memberService = new MemberService(memberRepository);
    
    @Test
    void getAll() {
        createDummy();

        Collection<Member> members = memberService.getAll();

        assertThat(members.size()).isEqualTo(2);
    }

    @Test
    void findMember() {
        createDummy();

        Member member = memberService.findMember("b@b.com", "password2");

        assertThat(member.getEmail()).isEqualTo("b@b.com");
        assertThat(member.getPassword()).isEqualTo("password2");
    }

    @DisplayName("해당하는 회원을 찾지 못하면 예외가 발생한다.")
    @ParameterizedTest
    @ArgumentsSource(InvalidMemberArgumentProvider.class)
    void findMemberWithException(String email, String password) {
        createDummy();

        assertThrows(NoSuchElementException.class, () -> {
            memberService.findMember(email, password);
        });
    }

    private void createDummy() {
        Member member1 = new Member(1L, "a@a.com", "password1");
        Member member2 = new Member(2L, "b@b.com", "password2");
        memberRepository.insert(member1);
        memberRepository.insert(member2);
    }

    static class InvalidMemberArgumentProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of("c@c.com", "password1"),
                    Arguments.of("a@a.com", "password3"),
                    Arguments.of("c@c.com", "password3")
            );
        }
    }
}
