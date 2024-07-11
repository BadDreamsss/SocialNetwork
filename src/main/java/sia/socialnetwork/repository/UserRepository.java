package sia.socialnetwork.repository;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.CrudRepository;
import sia.socialnetwork.data.UserTable;

@EnableMongoRepositories
public interface UserRepository extends CrudRepository<UserTable, Long> {
    UserTable findByUsername(String username);
}
