package com.galexandrov.zoo.repositories;

import com.galexandrov.zoo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsersRepository extends JpaRepository<User,Integer> {
   boolean existsByUsername(String username);
   Optional<User> findByEmailAndPassword(String email, String password);

   Optional<User> findByEmail(String email);
}
