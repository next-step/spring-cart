package cart.user.domain;

import java.util.List;

public interface UserRepository {
    UserEntity findByEmail(UserEmail email);

    List<UserEntity> findAll();
}
