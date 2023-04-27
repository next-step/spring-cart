package cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class JwpCartApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JwpCartApplication.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {

        jdbcTemplate.execute("DROP TABLE products IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE products(id INT NOT NULL AUTO_INCREMENT , name VARCHAR(255), image_url VARCHAR(255), price INT)");
    }
}
