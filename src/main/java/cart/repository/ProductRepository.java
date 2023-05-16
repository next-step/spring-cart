package cart.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

  private final JdbcTemplate jdbcTemplate;

  public Long save(String name, int price, String imageUrl) {
    String query = "INSERT INTO PRODUCT(NAME, PRICE, IMAGE_URL) VALUES(?, ?, ?)";
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(
        connection -> {
          PreparedStatement preparedStatement =
              connection.prepareStatement(query, new String[]{"id"});
          preparedStatement.setString(1, name);
          preparedStatement.setInt(2, price);
          preparedStatement.setString(3, imageUrl);

          return preparedStatement;
        },
        keyHolder);

    return keyHolder.getKey().longValue();
  }
}


//public class H2GameHistoryRepository implements BaseGameHistoryRepository {
//
//
//  public Integer save(String names, int count) {
//    String query = "INSERT INTO GAME_HISTORY(TRIAL_COUNT, CAR_NAMES) VALUES(?, ?)";
//    KeyHolder keyHolder = new GeneratedKeyHolder();
//
//    jdbcTemplate.update(
//        connection -> {
//          PreparedStatement preparedStatement =
//              connection.prepareStatement(query, new String[] {"id"});
//          preparedStatement.setInt(1, count);
//          preparedStatement.setString(2, names.replaceAll(" ", ""));
//
//          return preparedStatement;
//        },
//        keyHolder);
//
//    return keyHolder.getKey().intValue();
//  }
//
//  public void updateWinners(Integer gameId, String winners) {
//    String query = "UPDATE GAME_HISTORY SET WINNERS = ? where ID = ?";
//
//    jdbcTemplate.update(
//        connection -> {
//          PreparedStatement preparedStatement = connection.prepareStatement(query);
//
//          preparedStatement.setString(1, winners);
//          preparedStatement.setInt(2, gameId);
//
//          return preparedStatement;
//        });
//  }
//
//  public List<GameHistory> findAllWithHistory() {
//    String sql =
//        "SELECT GH.ID as gameId, GH.WINNERS AS winners, RH.NAME AS name, RH.POSITION AS position "
//            + "FROM GAME_HISTORY GH "
//            + "LEFT JOIN ROUND_HISTORY RH "
//            + "ON GH.ID = RH.GAME_ID "
//            + "WHERE GH.TRIAL_COUNT = RH.ROUND "
//            + "ORDER BY GH.ID, RH.POSITION DESC";
//
//    return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(GameHistory.class));
//  }
//}
