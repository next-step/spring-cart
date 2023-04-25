package cart.user.domain;

public interface UserRepository {
    UserEntity findByEmail(UserEmail email);
}
