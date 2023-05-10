package cart.member.persistence;

import cart.member.domain.entity.Member;
import cart.member.domain.repository.MemberRepository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MemberDao implements MemberRepository {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MemberDao(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Member> findAll() {
        return namedParameterJdbcTemplate.query(
                "select * from member",
                new MemberRowMapper()
        );
    }
}
