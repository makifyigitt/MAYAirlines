package com.may.MAYAirlines.repository;


import com.may.MAYAirlines.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    @Query("select c from Customer c where  c.status = true ")
    List<User> getAllActiveCustomers();
    @Query("select c from Customer c where  c.status = false ")
    List<User> getAllInactiveCustomers();
}
