package lab4.repository;

import lab4.entity.Logins;
import org.springframework.data.repository.CrudRepository;

public interface LoginsRepo extends CrudRepository<Logins,Long> {
}
