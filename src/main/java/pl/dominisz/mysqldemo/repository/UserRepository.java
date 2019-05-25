package pl.dominisz.mysqldemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dominisz.mysqldemo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
