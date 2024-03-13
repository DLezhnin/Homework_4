package lab4.repository;

import lab4.entity.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsersRepo extends CrudRepository<Users,Long> {
    Optional<Users> findByusername(String username);
}
