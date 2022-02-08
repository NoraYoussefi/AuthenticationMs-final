package org.lsi.repositories;

import org.lsi.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);
  User getByUsername(String username);
  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
