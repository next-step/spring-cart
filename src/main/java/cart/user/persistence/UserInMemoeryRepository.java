package cart.user.persistence;

import cart.user.domain.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserInMemoeryRepository implements UserRepository {

    private final Map<UserId, User> users = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1L);

    @Override
    public UserEntity findByEmail(UserEmail email) {
        return users.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getEmail().equals(email))
                .map(entry -> new UserEntity(entry.getKey(), entry.getValue()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("이메일에 해당하는 유저 정보가 없습니다."));
    }
}
