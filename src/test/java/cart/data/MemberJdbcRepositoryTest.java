package cart.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MemberJdbcRepositoryTest {

    @DisplayName("맴버목록 조회 테스트")
    @Test
    void getMembers() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:test-data.sql")
                .build();

        MemberJdbcRepository memberRepository = new MemberJdbcRepository(dataSource);
        assertThat(memberRepository.getMembers().size()).isEqualTo(2);
    }

    @DisplayName("email, pwd 로 조회 성공 테스트")
    @Test
    void getMemberSuccessTest() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:test-data.sql")
                .build();

        MemberJdbcRepository memberRepository = new MemberJdbcRepository(dataSource);
        assertThat(memberRepository.getMember("b@b.com", "password2"))
                .isNotEmpty();
    }

    @DisplayName("email, pwd 로 조회 실패 테스트")
    @Test
    void getMemberFailTest() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:test-data.sql")
                .build();
        MemberJdbcRepository memberRepository = new MemberJdbcRepository(dataSource);
        assertThat(memberRepository.getMember("c@c.com", "password3"))
                .isEmpty();
    }

}