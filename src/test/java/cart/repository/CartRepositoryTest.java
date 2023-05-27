package cart.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CartRepositoryTest {

  private CartRepository cartRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  void add() {

  }

  @Test
  void findById() {
  }

  @Test
  void findAll() {
  }
}
