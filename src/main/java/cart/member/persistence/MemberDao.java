package cart.member.persistence;

import cart.member.domain.entity.Member;
import cart.member.domain.repository.MemberRepository;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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

    @Override
    public Member findById(Long id) {
        try {
            return namedParameterJdbcTemplate.queryForObject(
                    "select * from member where id = :member_id",
                    new MapSqlParameterSource("member_id", id),
                    new MemberRowMapper()
            );
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new IllegalArgumentException("해당 id의 회원이 존재하지 않습니다.");
        }
    }
}
