package cart.dao;

import cart.domain.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO {
    private JdbcTemplate jdbcTemplate;

    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//
    public int insertProduct(Product product) {
        String sql = "insert into PRODUCT (product_name, product_price,product_imagename) values (?, ?, ?)";
        return jdbcTemplate.update(sql,product.getName() ,product.getPrice() ,product.getImagename() );
    }
//
//    public List<History> selectListPlayResult() {
//        String sql = "SELECT  FORMATDATETIME(created_at,'yyyymmddhhmmss') created_at   , winners, trialcount ,max(position) position  FROM PLAY_RESULT \n" +
//                "group by FORMATDATETIME(created_at,'yyyymmddhhmmss') , winners, trialcount";
//        return jdbcTemplate.query(
//                sql, (rs, rowNum) -> {
//                    History history = new History(
//                            rs.getString("winners"),
//                            rs.getInt("trialCount"),
//                            rs.getInt("position"),
//                            rs.getString("created_at")
//                    );
//                    return history;
//                });
//    }
//
//    public List<Car> selectListPlay(History historyParameter) {
//        String sql = "SELECT name,  position  FROM PLAY_RESULT \n" +
//                "WHERE FORMATDATETIME(created_at,'yyyymmddhhmmss') =  ? \n" +
//                "and    winners = ?  \n" +
//                "and    trialcount =  ?";
//        return jdbcTemplate.query(
//                sql, (rs, rowNum) -> {
//                    Car car = new Car(
//                            rs.getString("name"),
//                            rs.getInt("position")
//                    );
//                    return car;
//                }, historyParameter.getCreated_at()
//                , historyParameter.getWinners()
//                , historyParameter.getTrialCount());
//    }

}
