package cart.member.infra;

import cart.member.domain.Member;
import cart.member.domain.MemberRepository;
import cart.member.domain.Members;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberDao extends NamedParameterJdbcDaoSupport implements MemberRepository {

    private static final String TABLE_NAME = "member";
    private static final String ID = "id";
    private static final String[] COLUMNS = {"email", "password"};
    private static final MemberRowMapper ROW_MAPPER = new MemberRowMapper();
    private final SimpleJdbcInsert simpleJdbcInsert;

    public MemberDao(DataSource dataSource) {
        setDataSource(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(ID)
                .usingColumns(COLUMNS);
    }

    @Override
    public Members findAll() {
        final String query = String.format("SELECT * FROM %s", TABLE_NAME);
        List<Member> members = getNamedParameterJdbcTemplate().query(query, ROW_MAPPER);

        return new Members(members);
    }

    @Override
    public Optional<Member> fidByEmail(String email) {
        final String query = String.format("SELECT * FROM %s WHERE email = :email", TABLE_NAME);
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("email", email);

        Member member = getNamedParameterJdbcTemplate()
                .queryForObject(query, namedParameters, ROW_MAPPER);

        return Optional.ofNullable(member);
    }

    @Override
    public void save(Member member) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(member);
         simpleJdbcInsert.executeAndReturnKey(parameterSource);
    }
}
