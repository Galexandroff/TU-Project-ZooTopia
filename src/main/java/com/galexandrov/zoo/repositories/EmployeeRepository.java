package com.galexandrov.zoo.repositories;

import com.galexandrov.zoo.models.Employee;
import com.galexandrov.zoo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

   // boolean existsByUsername(String username);

   Optional<Employee> findByCodeAndPassword(String code, String password);

   //Optional<Employee> findByUsername(String username);
}
