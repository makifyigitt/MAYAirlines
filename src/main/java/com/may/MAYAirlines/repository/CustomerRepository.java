package com.may.MAYAirlines.repository;

import com.may.MAYAirlines.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Optional<Customer> findByUsername(String username);
    @Query("select c from Customer c where  c.status = true ")
    List<Customer> getAllActiveCustomers();
    @Query("select c from Customer c where  c.status = false ")
    List<Customer> getAllInactiveCustomers();

}
