package cart.repository;

import cart.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);

    List<User> findAll();

    Optional<User> findById(Long id);
}
